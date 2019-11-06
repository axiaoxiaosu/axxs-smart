<template>
  <div>
    <div :class="{'is-active':isActive}" class="hamburger" @click="toggleClick">
      <i class="fa fa-navicon fa-2x" style="color: #409EFF"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',
  props: {
    isActive: {
      type: Boolean,
      default: false
    },
    toggleClick: {
      type: Function,
      default: null
    }
  }
}
</script>

<style scoped>
.hamburger {
	display: inline-block;
	/*cursor: pointer;*/
	width: 20px;
	height: 20px;
	transform: rotate(90deg);
	transition: .38s;
	transform-origin: 70% 130%;
}
.hamburger.is-active {
	transform: rotate(0deg);
}
</style>
