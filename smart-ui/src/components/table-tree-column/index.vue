<template>
  <el-table-column :prop="prop" v-bind="$attrs">
    <template slot-scope="scope">
      <span :style="childStyles(scope.row)" @click.prevent="toggleHandle(scope.$index, scope.row)">
        <i :class="iconClasses(scope.row)" :style="iconStyles(scope.row)"/>
        {{ scope.row[prop] }}
      </span>
    </template>
  </el-table-column>
</template>

<script>
import isArray from 'lodash/isArray'
export default {
  name: 'TableTreeColumn',
  props: {
    prop: {
      type: String,
      default: 'prop'
    },
    treeKey: {
      type: String,
      default: 'id'
    },
    parentKey: {
      type: String,
      default: 'parentId'
    },
    levelKey: {
      type: String,
      default: '_level'
    },
    childKey: {
      type: String,
      default: 'children'
    }
  },
  methods: {
    childStyles(row) {
      return { 'padding-left': (row[this.levelKey] > 1 ? row[this.levelKey] * 7 : 0) + 'px' }
    },
    iconClasses(row) {
      return [!row._expanded ? 'el-icon-caret-right' : 'el-icon-caret-bottom']
    },
    iconStyles(row) {
      return { 'visibility': this.hasChild(row) ? 'visible' : 'hidden' }
    },
    hasChild(row) {
      return row[this.childKey].length >= 1 || false
      /* return (isArray(row[this.childKey]) && row[this.childKey].length >= 1) || false*/
    },
    // 切换处理
    toggleHandle(index, row) {
      if (this.hasChild(row)) {
        let data = this.$parent.store.states.data.slice(0)
        if (data[index].children.length > 0) {
          data[index]._expanded = true
        }
        if (data[index]._expanded) {
          data = data.splice(0, index + 1).concat(row[this.childKey]).concat(data)
        } else {
          data = this.removeChildNode(data, row[this.treeKey])
        }
        this.$parent.store.commit('setData', data)
        this.$nextTick(() => {
          this.$parent.doLayout()
        })
      }
    },
    // 移除子节点
    removeChildNode(data, parentId) {
      const parentIds = isArray(parentId) ? parentId : [parentId]
      if (parentId.length <= 0) {
        return data
      }
      const ids = []
      for (let i = 0; i < data.length; i++) {
        if (parentIds.indexOf(data[i][this.parentKey]) !== -1 && parentIds.indexOf(data[i][this.treeKey]) === -1) {
          data[i]._expanded = false
          ids.push(data.splice(i, 1)[0][this.treeKey])
          i--
        }
      }
      return this.removeChildNode(data, ids)
    }
  }
}
</script>
