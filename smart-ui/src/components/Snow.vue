<template>
  <div id="particles-js"/>
</template>

<script>
import snow from '../assets/snow.png'
export default {
  name: 'Snow',
  mounted() {
    require('particles.js')
    this.$nextTick(() => {
      this.initParticlesJS()
    })
  },

  methods: {
    initParticlesJS() {
      /* eslint-disable */
      particlesJS("particles-js", {
       //生成粒子数量
        "particles": {
          "number": {
            "value": 20,
            //密度
            "density": {
              "enable": true,
              "value_area": 500
            }
          },
          "color": {
            "value": "#a0eee1"
          },
          //显示形状
          "shape": {
            "type": "image",
            "stroke": {
              "width": 3,
              "color": "#fff"
            },
            "polygon": {
              "nb_sides": 5
            },
            "image": {
            //  "src": "http://www.dynamicdigital.us/wp-content/uploads/2013/02/starburst_white_300_drop_2.png",
              "src":snow,
              "width": 100,
              "height": 100
            }
          },
          /*不透明度*/
          "opacity": {
            "value":0.7,
            "random": false,
            "anim": {
              "enable": false,
              "speed": 1,
              "opacity_min": 0.1,
              "sync": false
            }
          },
          /*粒子大小*/
          "size": {
            "value": 5,
            "random": false,
            "anim": {
              "enable": false,
              "speed": 20,
              "size_min": 0.1,
              "sync": false
            }
          },
          "line_linked": {
            "enable": false,
            "distance": 50,
            "color": "#2e3dff",
            "opacity": 0.1,
            "width": 1
          },
          "move": {
            "enable": true,
            "speed": 5,
            "direction": "bottom",
            "random": true,
            "straight": false,
            "out_mode": "out",
            "bounce": false,
            "attract": {
              "enable": true,
              "rotateX": 300,
              "rotateY": 1200
            }
          }
        },
        "interactivity": {
          "detect_on": "canvas",
          "events": {
            "resize": false,
            /*悬停效果*/
            "onhover":{
              "enable":false,
              "mode":"repulse"
            }
          },
          "modes": {
            "grab": {
              "distance": 150,
              "line_linked": {
                "opacity": 1
              }
            },
            "bubble": {
              "distance": 200,
              "size": 40,
              "duration": 2,
              "opacity": 8,
              "speed": 3
            },
            "repulse": {
              "distance": 200,
              "duration": 0.2
            },
            "push": {
              "particles_nb": 4
            },
            "remove": {
              "particles_nb": 2
            }
          }
        },
        "retina_detect": false
      });
      }
    }

  }
</script>

<style scoped>

</style>
