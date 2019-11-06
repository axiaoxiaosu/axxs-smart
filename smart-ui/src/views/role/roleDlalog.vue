<template>
  <el-dialog
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :visible.sync="visible"
    @close="closeDialog"
  >
    <el-form>
      <el-button @click="visible = false">取消</el-button>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'AddRole',
  data: function() {
    return {
      visible: true
    }
  },
  methods: {
    // 调用父组件函数改变状态
    closeDialog() {
      this.$emit('handlerClose')
    }
  }
}
</script>

<style scoped>

</style>
