<!--
<template>
  <div class="demo-swiper">
    <swiper ref="swiperTop" :options="swiperOptionTop" class="gallery-top">
      <swiper-slide class="slide-1"><span>1</span></swiper-slide>
      <swiper-slide class="slide-2"><span>2</span></swiper-slide>
      <swiper-slide class="slide-3"><span>3</span></swiper-slide>
      <div slot="button-next" class="swiper-button-next swiper-button-white"/>
      <div slot="button-prev" class="swiper-button-prev swiper-button-white"/>
    </swiper>
    &lt;!&ndash; swiper2 Thumbs &ndash;&gt;
    <swiper ref="swiperThumbs" :options="swiperOptionThumbs" class="gallery-thumbs">
      <swiper-slide class="slide-1"><span>1</span></swiper-slide>
      <swiper-slide class="slide-2"><span>2</span></swiper-slide>
      <swiper-slide class="slide-3"><span>3</span></swiper-slide>
    </swiper>
  </div>
</template>

<script>
import 'swiper/dist/css/swiper.css'
import { swiper, swiperSlide } from 'vue-awesome-swiper'
export default {
  name: 'BannerV2',
  components: { swiper, swiperSlide },
  props: {
    slide: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  data() {
    return {
      swiperOptionTop: {
        spaceBetween: 10,
        loop: true,
        loopedSlides: 5, // looped slides should be the same
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      },
      swiperOptionThumbs: {
        spaceBetween: 10,
        slidesPerView: 4,
        touchRatio: 0.2,
        loop: true,
        loopedSlides: 5, // looped slides should be the same
        slideToClickedSlide: true
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      const swiperTop = this.$refs.swiperTop.swiper
      const swiperThumbs = this.$refs.swiperThumbs.swiper
      swiperTop.controller.control = swiperThumbs
      swiperThumbs.controller.control = swiperTop
    })
  }
}
</script>

<style lang="scss" scoped>
  .swiper-container {
    background-color: #f6d493;
  }
  .swiper-slide {
    background-size: cover;
    background-position: center;
    &.slide-1 {
      background-image:url('/src/assets/swiper/img-1.jpg');
    }
    &.slide-2 {
      background-image:url('/src/assets/swiper/img-2.jpg');
    }
    &.slide-3 {
      background-image:url('/src/assets/swiper/img-3.jpg');
    }
  }
  .gallery-top {
    height: 80%!important;
    width: 100%;
  }
  .gallery-thumbs {
    height: 20%!important;
    box-sizing: border-box;
    padding: 10px 0;
  }
  .gallery-thumbs .swiper-slide {
    width: 25%;
    height: 100%;
    opacity: 0.4;
  }
  .gallery-thumbs .swiper-slide-active {
    opacity: 1;
  }
</style>
-->
