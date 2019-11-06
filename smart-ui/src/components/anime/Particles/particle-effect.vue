<script>
const VALID_DIRECTION = new Set(['left', 'right', 'top', 'bottom'])
const VALID_PARTICLE_TYPE = new Set(['circle', 'rectangle', 'triangle'])
const VALID_PARTICLE_STYLE = new Set(['fill', 'stroke'])
const DEFAULT_SIZE_FUNC = () => Math.floor(Math.random() * 3 + 1)
const DEFAULT_SPEED_FUNC = () => rand(4)

const bezier = (() => {
  const kSplineTableSize = 11
  const kSampleStepSize = 1.0 / (kSplineTableSize - 1.0)

  function A(aA1, aA2) {
    return 1.0 - 3.0 * aA2 + 3.0 * aA1
  }
  function B(aA1, aA2) {
    return 3.0 * aA2 - 6.0 * aA1
  }
  function C(aA1) {
    return 3.0 * aA1
  }

  function calcBezier(aT, aA1, aA2) {
    return ((A(aA1, aA2) * aT + B(aA1, aA2)) * aT + C(aA1)) * aT
  }
  function getSlope(aT, aA1, aA2) {
    return 3.0 * A(aA1, aA2) * aT * aT + 2.0 * B(aA1, aA2) * aT + C(aA1)
  }

  function binarySubdivide(aX, aA, aB, mX1, mX2) {
    let currentX
    let currentT
    let i = 0
    do {
      currentT = aA + (aB - aA) / 2.0
      currentX = calcBezier(currentT, mX1, mX2) - aX
      if (currentX > 0.0) {
        aB = currentT
      } else {
        aA = currentT
      }
    } while (Math.abs(currentX) > 0.0000001 && ++i < 10)
    return currentT
  }

  function newtonRaphsonIterate(aX, aGuessT, mX1, mX2) {
    for (let i = 0; i < 4; ++i) {
      const currentSlope = getSlope(aGuessT, mX1, mX2)
      if (currentSlope === 0.0) return aGuessT
      const currentX = calcBezier(aGuessT, mX1, mX2) - aX
      aGuessT -= currentX / currentSlope
    }
    return aGuessT
  }

  function bezier(mX1, mY1, mX2, mY2) {
    if (!(mX1 >= 0 && mX1 <= 1 && mX2 >= 0 && mX2 <= 1)) {
      return x => x
    }
    const sampleValues = new Float32Array(kSplineTableSize)

    if (mX1 !== mY1 || mX2 !== mY2) {
      for (let i = 0; i < kSplineTableSize; ++i) {
        sampleValues[i] = calcBezier(i * kSampleStepSize, mX1, mX2)
      }
    }

    function getTForX(aX) {
      let intervalStart = 0.0
      let currentSample = 1
      const lastSample = kSplineTableSize - 1

      for (
        ;
        currentSample !== lastSample && sampleValues[currentSample] <= aX;
        ++currentSample
      ) {
        intervalStart += kSampleStepSize
      }

      --currentSample

      const dist =
        (aX - sampleValues[currentSample]) /
        (sampleValues[currentSample + 1] - sampleValues[currentSample])
      const guessForT = intervalStart + dist * kSampleStepSize
      const initialSlope = getSlope(guessForT, mX1, mX2)

      if (initialSlope >= 0.001) {
        return newtonRaphsonIterate(aX, guessForT, mX1, mX2)
      } else if (initialSlope === 0.0) {
        return guessForT
      } else {
        return binarySubdivide(
          aX,
          intervalStart,
          intervalStart + kSampleStepSize,
          mX1,
          mX2
        )
      }
    }

    return x => {
      if (mX1 === mY1 && mX2 === mY2) return x
      if (x === 0) return 0
      if (x === 1) return 1
      return calcBezier(getTForX(x), mY1, mY2)
    }
  }

  return bezier
})()

const easings = (() => {
  const names = [
    'Quad',
    'Cubic',
    'Quart',
    'Quint',
    'Sine',
    'Expo',
    'Circ',
    'Back'
  ]

  const equations = {
    In: [
      [0.55, 0.085, 0.68, 0.53] /* InQuad */,
      [0.55, 0.055, 0.675, 0.19] /* InCubic */,
      [0.895, 0.03, 0.685, 0.22] /* InQuart */,
      [0.755, 0.05, 0.855, 0.06] /* InQuint */,
      [0.47, 0.0, 0.745, 0.715] /* InSine */,
      [0.95, 0.05, 0.795, 0.035] /* InExpo */,
      [0.6, 0.04, 0.98, 0.335] /* InCirc */,
      [0.6, -0.28, 0.735, 0.045] /* InBack */
    ],
    Out: [
      [0.25, 0.46, 0.45, 0.94] /* OutQuad */,
      [0.215, 0.61, 0.355, 1.0] /* OutCubic */,
      [0.165, 0.84, 0.44, 1.0] /* OutQuart */,
      [0.23, 1.0, 0.32, 1.0] /* OutQuint */,
      [0.39, 0.575, 0.565, 1.0] /* OutSine */,
      [0.19, 1.0, 0.22, 1.0] /* OutExpo */,
      [0.075, 0.82, 0.165, 1.0] /* OutCirc */,
      [0.175, 0.885, 0.32, 1.275] /* OutBack */
    ],
    InOut: [
      [0.455, 0.03, 0.515, 0.955] /* InOutQuad */,
      [0.645, 0.045, 0.355, 1.0] /* InOutCubic */,
      [0.77, 0.0, 0.175, 1.0] /* InOutQuart */,
      [0.86, 0.0, 0.07, 1.0] /* InOutQuint */,
      [0.445, 0.05, 0.55, 0.95] /* InOutSine */,
      [1.0, 0.0, 0.0, 1.0] /* InOutExpo */,
      [0.785, 0.135, 0.15, 0.86] /* InOutCirc */,
      [0.68, -0.55, 0.265, 1.55] /* InOutBack */
    ]
  }

  const functions = {
    linear: bezier(0.25, 0.25, 0.75, 0.75)
  }

  for (const type in equations) {
    equations[type].forEach((f, i) => {
      functions['ease' + type + names[i]] = bezier.apply(this, f)
    })
  }

  return functions
})()

function rand(value) {
  return Math.random() * value - value / 2
}

function isFunc(value) {
  return typeof value === 'function'
}

const me = new WeakMap()

export default {
  props: {
    hidden: {
      type: Boolean,
      default: false
    },
    direction: {
      type: String,
      default: 'left',
      validator(value) {
        return VALID_DIRECTION.has(value)
      }
    },
    particleType: {
      type: String,
      default: 'circle',
      validator(value) {
        return VALID_PARTICLE_TYPE.has(value)
      }
    },
    particleStyle: {
      type: String,
      default: 'fill',
      validator(value) {
        return VALID_PARTICLE_STYLE.has(value)
      }
    },
    particleColor: {
      type: String,
      default: '#000'
    },
    duration: {
      type: Number,
      default: 1000
    },
    easing: {
      type: [String, Array],
      default: 'easeInOutCubic',
      validator(value) {
        if (Array.isArray(value)) {
          return value.length === 4 && value.every(Number.isFinite)
        } else {
          return value != null
        }
      }
    },
    canvasPadding: {
      type: Number,
      default: 150
    },
    size: {
      type: [Number, Function],
      default: () => DEFAULT_SIZE_FUNC
    },
    speed: {
      type: [Number, Function],
      default: () => DEFAULT_SPEED_FUNC
    },
    particlesAmountCoefficient: {
      type: Number,
      default: 3
    },
    oscillationCoefficient: {
      type: Number,
      default: 20
    }
  },
  data() {
    return {
      status: this.hidden ? 'hidden' : 'normal',
      progress: this.hidden ? 0 : 1,
      rect: {
        width: 0,
        height: 0
      }
    }
  },
  computed: {
    canvasStyles() {
      const style = {}
      if (this.status === 'hidden' || this.status === 'normal') {
        style.visibility = 'hidden'
      }
      return style
    },
    wrapperStyles() {
      const style = {}
      const prop = this.transformStyleProp
      const px = this.transformStylePx

      if (this.status === 'hiding' || this.status === 'showing') {
        style.transform = `${prop}(${px}px)`
      } else if (this.status === 'hidden') {
        style.visibility = 'hidden'
      }

      return style
    },
    contentStyles() {
      const style = {}
      const prop = this.transformStyleProp
      const px = -this.transformStylePx

      if (this.status === 'hiding' || this.status === 'showing') {
        style.transform = `${prop}(${px}px)`
      }

      return style
    },
    isHorizontal() {
      return this.direction === 'left' || this.direction === 'right'
    },
    transformStyleProp() {
      return this.isHorizontal ? 'translateX' : 'translateY'
    },
    transformStylePx() {
      const size = this.isHorizontal ? this.rect.width : this.rect.height
      const value =
        this.direction === 'left' || this.direction === 'top'
          ? this.progress
          : -this.progress

      return this.direction === 'left' || this.direction === 'top'
        ? Math.ceil(size * value)
        : Math.floor(size * value)
    }
  },
  watch: {
    hidden(newValue) {
      if (this.status === 'normal' && newValue) {
        this.status = 'hiding'
        this.$nextTick(this.startAnimation)
      } else if (this.status === 'hidden' && !newValue) {
        this.status = 'showing'
        this.$nextTick(this.startAnimation)
      }
    }
  },
  created() {
    me.set(this, {
      particles: [],
      lastProgress: 0,
      inAnimation: false,
      beginTimeStamp: 0,
      endTimeStamp: 0,
      easing: easings.linear
    })
  },
  updated() {
    window.requestAnimationFrame(this.afterDomUpdate)
  },
  methods: {
    afterDomUpdate(now) {
      if (now < me.get(this).endTimeStamp) {
        this.doAnimation(now)
        return
      }
      switch (this.status) {
        case 'hiding':
          this.progress = 1
          break
        case 'showing':
          this.progress = 0
          break
        default:
          break
      }
    },
    startAnimation() {
      if (!this.$refs) return
      if (!this.$refs.canvasRef) return
      if (!this.$refs.wrapperRef) return

      const data = me.get(this)

      if (this.status === 'hiding') {
        data.lastProgress = 0
        this.progress = 1
      } else {
        data.lastProgress = 1
        this.progress = 0
      }
      if (Array.isArray(this.easing)) {
        data.easing = bezier.apply(this, this.easing)
      } else {
        data.easing = easings[this.easing] || easings.linear
      }
      data.beginTimeStamp = window.performance.now()
      data.endTimeStamp = data.beginTimeStamp + this.duration
      data.particles = []

      const wrapperRect = this.$refs.wrapperRef.getBoundingClientRect()
      this.$refs.canvasRef.width = wrapperRect.width + this.canvasPadding * 2
      this.$refs.canvasRef.height = wrapperRect.height + this.canvasPadding * 2
      this.rect.width = wrapperRect.width
      this.rect.height = wrapperRect.height

      this.$emit('begin')
      this.$nextTick(() => this.doAnimation(data.beginTimeStamp))
    },
    doAnimation(timestamp) {
      const { beginTimeStamp, endTimeStamp, easing } = me.get(this)
      const now = Math.min(timestamp, endTimeStamp)
      let p = (now - beginTimeStamp) / this.duration

      if (this.status === 'showing') {
        p = 1 - p
      }
      this.progress = easing(p)

      if (this.duration) {
        this.addParticles(p)
      }
    },
    loop() {
      this.updateParticles()
      this.renderParticles()

      const data = me.get(this)

      if (data.particles.length) {
        data.inAnimation = true
        window.requestAnimationFrame(this.loop)
      } else {
        data.inAnimation = false
        this.cycleStatus()
        this.$emit('complete')
      }
    },
    addParticles(progress) {
      const { width, height } = this.rect
      const { lastProgress, inAnimation } = me.get(this)
      const delta =
        this.status === 'hiding'
          ? progress - lastProgress
          : lastProgress - progress
      const progressValue =
        (this.isHorizontal ? width : height) * progress +
        delta * (this.status === 'hiding' ? 1000 : 100)

      me.get(this).lastProgress = progress

      let x = this.canvasPadding
      let y = this.canvasPadding

      if (this.isHorizontal) {
        x += this.direction === 'left' ? progressValue : width - progressValue
      } else {
        y += this.direction === 'top' ? progressValue : height - progressValue
      }

      let i = Math.floor(this.particlesAmountCoefficient * (delta * 100 + 1))
      if (i > 0) {
        while (i--) {
          this.addParticle(
            x + (this.isHorizontal ? 0 : width * Math.random()),
            y + (this.isHorizontal ? height * Math.random() : 0)
          )
        }
      }

      if (!inAnimation) {
        window.requestAnimationFrame(this.loop)
        me.get(this).inAnimation = true
      }
    },
    addParticle(startX, startY) {
      const frames = this.duration * 60 / 1000
      const speed = isFunc(this.speed) ? this.speed() : this.speed
      const size = isFunc(this.size) ? this.size() : this.size

      me.get(this).particles.push({
        startX,
        startY,
        x: this.status === 'hiding' ? 0 : speed * -frames,
        y: 0,
        angle: rand(360),
        counter: this.status === 'hiding' ? 0 : frames,
        increase: Math.PI * 2 / 100,
        life: 0,
        death:
          this.status === 'hiding' ? frames - 20 + Math.random() * 40 : frames,
        speed,
        size
      })
    },
    updateParticles() {
      const { particles } = me.get(this)

      for (let i = 0; i < particles.length; i++) {
        const p = particles[i]

        if (p.life > p.death) {
          particles.splice(i, 1)
        } else {
          p.x += p.speed
          p.y = this.oscillationCoefficient * Math.sin(p.counter * p.increase)
          p.life++
          p.counter += this.status === 'hiding' ? 1 : -1
        }
      }
    },
    renderParticles() {
      const canvas = this.$refs.canvasRef
      const ctx = canvas.getContext('2d')
      const { particles } = me.get(this)

      ctx.clearRect(0, 0, canvas.width, canvas.height)
      ctx.fillStyle = ctx.strokeStyle = this.particleColor

      for (let i = 0; i < particles.length; ++i) {
        const p = particles[i]

        if (p.life < p.death) {
          ctx.translate(p.startX, p.startY)
          ctx.rotate(p.angle * Math.PI / 180)
          ctx.globalAlpha =
            this.status === 'hiding' ? 1 - p.life / p.death : p.life / p.death
          ctx.beginPath()

          if (this.particleType === 'circle') {
            ctx.arc(p.x, p.y, p.size, 0, 2 * Math.PI)
          } else if (this.particleType === 'triangle') {
            ctx.moveTo(p.x, p.y)
            ctx.lineTo(p.x + p.size, p.y + p.size)
            ctx.lineTo(p.x + p.size, p.y - p.size)
          } else if (this.particleType === 'rectangle') {
            ctx.rect(p.x, p.y, p.size, p.size)
          }

          if (this.particleStyle === 'fill') {
            ctx.fill()
          } else if (this.particleStyle === 'stroke') {
            ctx.closePath()
            ctx.stroke()
          }

          ctx.globalAlpha = 1
          ctx.rotate(-p.angle * Math.PI / 180)
          ctx.translate(-p.startX, -p.startY)
        }
      }
    },
    cycleStatus() {
      switch (this.status) {
        case 'normal':
          this.status = 'hiding'
          break
        case 'hidden':
          this.status = 'showing'
          break
        case 'hiding':
          this.status = 'hidden'
          break
        case 'showing':
          this.status = 'normal'
          break
        default:
          break
      }
    }
  }
}
</script>

<template>
  <div :class="$style.particles">
    <div
      ref="wrapperRef"
      :style="wrapperStyles"
      :class="$style.wrapper"
    >
      <div
        :style="contentStyles"
        :class="$style.content"
      >
        <slot />
      </div>
    </div>
    <canvas
      ref="canvasRef"
      :style="canvasStyles"
      :class="$style.canvas"
    />
  </div>
</template>

<style module>
.particles {
  position: relative;
  display: inline-block;
}

.wrapper {
  position: relative;
  display: inline-block;
  overflow: hidden;
}

.content:focus,
.content > *:focus {
  outline: none;
}

.canvas {
  position: absolute;
  top: 50%;
  left: 50%;
  pointer-events: none;
  transform: translate3d(-50%, -50%, 0);
}
</style>
