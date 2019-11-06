<script>
import ParticleEffect from './particle-effect'

export default {
  props: {
    text: {
      type: String,
      default: '11'
    },
    hidden: {
      type: Boolean,
      default: false
    },
    animating: {
      type: Boolean,
      default: false
    },
    buttonStyles: {
      type: Object,
      default: null
    },
    buttonOptions: {
      type: Object,
      default: null
    }
  },
  computed: {
    particleEffectProps() {
      const result = { hidden: this.hidden }

      Object.keys(this.buttonOptions).forEach(key => {
        if (key === 'hidden') return

        result[key] = this.buttonOptions[key]
      })

      return result
    },
    nativeButtonStyles() {
      const result = {
        background: '#121019',
        color: '#fff',
        padding: '1.5rem 3rem',
        border: '0',
        borderRadius: '5px',
        cursor: 'pointer',
        fontSize: '1.2em'
      }

      Object.keys(this.buttonStyles).forEach(key => {
        result[key] = this.buttonStyles[key]
      })

      return result
    }
  },
  methods: {
    onAnimationComplete() {
      this.$emit('update:animating', false)
    },
    onToggle() {
      if (this.animating) return

      this.$emit('update:hidden', !this.hidden)
      this.$emit('update:animating', true)
    }
  },
  render(createElement) {
    return createElement(
      ParticleEffect,
      {
        props: this.particleEffectProps,
        on: {
          complete: this.onAnimationComplete
        }
      },
      [
        createElement(
          'button',
          {
            style: this.nativeButtonStyles,
            on: {
              click: this.onToggle
            }
          },
          this.text
        )
      ]
    )
  }
}
</script>
