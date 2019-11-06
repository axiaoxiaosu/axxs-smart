<template>
  <div v-if="sidebar.opened!='ttt'" class="pageOverAll" >
    <div ref="galleryTop" class="swiper-container gallery-top" >
      <div class="swiper-wrapper">
        <div v-for="(s,index) in slide" :key="'wrapper'+index" class="swiper-slide">
          <img :src="s" style="width: 100%;height: 100%">
        </div>
      </div>
      <div ref="buttonNext" />
      <div ref="buttonPrev" />
    </div>
    <div ref="galleryThumbs" class="swiper-container gallery-thumbs" >
      <div class="swiper-wrapper">
        <div v-for="(s,index) in slide" :key="'wrapper'+index" class="swiper-slide">
          <img :src="s" style="width: 100%;height: 100%">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Swiper from 'swiper'
import 'swiper/css/swiper.min.css'
export default {
  name: 'SmartBanner',
  props: {
    slide: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ])
  },
  mounted() {
    const _self = this
    const galleryThumbs = new Swiper(_self.$refs.galleryThumbs, {
      spaceBetween: 10,
      slidesPerView: 3,
      loop: true,
      freeMode: true,
      loopedSlides: 5, // looped slides should be the same
      watchSlidesVisibility: true,
      watchSlidesProgress: true
    })
    const galleryTop = new Swiper(_self.$refs.galleryTop, {
      /* 定时*/
      autoplay: {
        delay: 2000,
        stopOnLastSlide: false
      },
      spaceBetween: 10,
      loop: true,
      loopedSlides: 5, // looped slides should be the same
      navigation: {
        nextEl: _self.$refs.buttonNext,
        prevEl: _self.$refs.buttonPrev
      },
      thumbs: {
        swiper: galleryThumbs
      },
      on: {
        click: (e) => {
          const url = 'https://www.aliyun.com/1111/2019/group-buying-share?ptCode=587367996FF3A7490E374F776F14E68F647C88CF896EF535&userCode=2rwvlg4w&share_source=copy_link'
          if (galleryTop.activeIndex === 2 || galleryTop.activeIndex === 5) {
            window.open(url)
          }
        }
      }
    })
  },

  methods: {
    onmouseenter() {
      const _self = this
      _self.$refs.galleryTop.swiper.autoplay.stop()
      _self.$refs.galleryThumbs.swiper.autoplay.stop()
    },
    onmouseleave() {
      const _self = this
      _self.$refs.galleryTop.swiper.autoplay.start()
      _self.$refs.galleryThumbs.swiper.autoplay.start()
    }
  }
}
</script>

<style scoped>

  .pageOverAll{
    width: 100%;
    height: 100%;
  }

  .swiper-container {
    width: 100%;
    margin-left: auto;
    margin-right: auto;
  }

  .swiper-wrapper{
    height: 100%;
    width: 100%;
  }

  .swiper-slide {
    height: 100%;
    width: 100%;
    background-size: cover;
    background-position: center;
  }

  .swiper-slide img{
    width: 100%;
    height: 100%;
  }
  .gallery-top {
    height: 70%;
    width: 100%;
  }
  .gallery-thumbs {
    width: 100%;
    height: 50%;
    box-sizing: border-box;
    padding: 10px 0;
  }
  .gallery-thumbs .swiper-slide {
    opacity: 0.4;
  }
  .gallery-thumbs .swiper-slide-thumb-active {
    opacity: 1;
  }

</style>
