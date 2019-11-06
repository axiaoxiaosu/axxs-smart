<template>
  <div>
    <el-button v-for="(button,index) in handle" :key="index" type="text" @click="commit(button.url)">{{ button.name }}</el-button>
  </div>
</template>

<script>
export default {
  name: 'JumpButton',
  props: {
    handle: {
      default: () => { return [] },
      type: Array,
      requires: true
    }
  },
  created() {
  },
  methods: {
    commit(func) {
      func()
    }
  }
}
</script>

<style scoped>

</style>
