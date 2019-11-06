<template>
  <div>
    <el-tree
      :name="option.name"
      :data="treeData"
      :default-checked-keys="option.checkedKeys"
      :default-expanded-keys="option.expandedKeys"
      :node-key="option.key"
      :show-checkbox="option.checkbox"/>
  </div>
</template>

<script>
export default {
  name: 'MTree',
  props: {
    treeData: {
      type: Array,
      default: () => {
        return []
      }
    },
    option: {
      type: Object,
      default: () => {
        return {
          checkbox: true,
          key: 'id',
          expandedKeys: [],
          checkedKeys: []
        }
      }
    }
  },
  data: function() {
    return {
      data: this.treeData
    }
  }

}
</script>

<style scoped>

</style>
