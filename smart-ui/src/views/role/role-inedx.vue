<template>
  <div>
    <div style="margin:10px 0">
      <el-card>
        <div style="display: flex">
          <div style="width: 10vw">
            <el-input ref="search" v-model="searchData.roleName" placeholder="输入角色名称"/>
          </div>
          <div style="padding-left: 2vw">
            <el-button type="primary" @click="getData">查询</el-button>
            <el-button type="primary" @click="addRoleSub">添加</el-button>
          </div>
        </div>
      </el-card>
    </div>
    <el-card style="height: 70vh">
      <el-table
        v-loading="tableLoading"
        :data="page.tableData"
        element-loading-text="加载中,请稍后"
        stripe
        align="center"
        height="60vh"
        class="roleMain-table"
        @selection-change="(e)=>{multitermData=e}">
        <el-table-column
          align="center"
          fixed
          prop="roleName"
          label="角色名称"
        />
        <el-table-column
          align="center"
          prop="status"
          label="角色状态"
        />
        <el-table-column
          align="center"
          prop="createName"
          label="创建人"
        />
        <el-table-column
          show-overflow-tooltip
          align="center"
          sortable
          prop="createTime"
          label="创建时间"
        />
        <el-table-column
          align="center"
          prop="updateTime"
          label="修改时间"
        />
        <el-table-column
          align="center"
          prop="updateUser"
          label="修改人"
        />
        <el-table-column
          align="center"
        >
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-dropdown
                @command="(e)=>{buttonCommit(e,scope)}"
              >
                <span class="el-dropdown-link">
                  <el-button type="primary">功能<i class="el-icon-arrow-down el-icon--right"/></el-button>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    v-for="(button,index) in buttonHandle"
                    :key="index"
                    :command="button"
                  >{{ button.title }}
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
      <!--批量操作栏-->
      <div class="batch-operate-container">
        <el-select
          v-model="operateType"
          size="small"
          placeholder="批量操作">
          <el-option
            v-for="item in operates"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        <el-button
          style="margin-left: 20px"
          class="search-button"
          type="primary"
          size="small"
          @click="handleBatchOperate()">
          确定
        </el-button>
      </div>
      <!--分页栏-->
      <div class="pagination-container">
        <el-pagination
          :page-size="page.size"
          :total="page.totalPage"
          :pager-count="5"
          background
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
      </div>
    </el-card>
    <template>
      <!--基本操作弹框-->
      <el-dialog
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :visible.sync="dialogMark"
        :title="dialog.tag.title"
        width="30%"
      >
        <el-form ref="ruleForm" :model="dialog.fromData" label-width="100px">
          <el-form-item label="角色名称" prop="name">
            <el-input :disabled="dialog.readonly" v-model="dialog.fromData.roleName" style="width: 50%"/>
          </el-form-item>
        </el-form>
        <span slot="footer" class="el-dialog__footer">
          <div v-if="dialog.readonly">
            <el-button type="primary" @click="dialogMark=false">取消</el-button>
          </div>
          <div v-else>
            <el-button type="primary" @click="submitForm('ruleForm',dialog.tag.action)">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </div>
        </span>
      </el-dialog>
      <!--关联菜单-->
      <el-dialog
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :visible.sync="mentDialogMark"
        :title="dialog.tag.title"
        width="70%"
        @open="initMentDialog"
      >
        <span slot="footer" class="el-dialog__footer">
          <MTree ref="tree" :tree-data="resource" :option="treerOption"/>
          <div>
            <el-button type="primary" @click="treeSubmit(dialog.tag.action)">提交</el-button>
          </div>
        </span>
      </el-dialog>
    </template>
  </div>
</template>

<script>

// action
import { addRole, deleteRole, getRoles, roleBindMent, updateRole, mensByRole } from '@/api/role'
import { getMenus } from '@/api/ment'

// component
import AddRole from '@/views/role/roleDlalog'
import MTree from '@/components/tree/mTree.vue'

export default {
  name: 'Role',
  components: {
    AddRole,
    MTree
  },
  data: function() {
    return {
      resource: [],
      tableLoading: false,
      dialogMark: false,
      mentDialogMark: false,
      page: {
        tableData: [],
        size: 0,
        totalPage: 0
      },
      operateType: null,
      multitermData: [],
      operates: [
        {
          label: '删除',
          value: 1
        }
      ],
      searchData: {
        roleName: '',
        pageNum: 1,
        pageSize: 10
      },
      button: {
        addRole: {
          name: 'add',
          title: '添加',
          action: addRole,
          icon: ''
        }
      },
      // 每一行的操作按钮  name必填   title 必填    action 向服务器提交 icon 图标
      buttonHandle: {
        getRole: {
          name: 'get',
          title: '查看',
          icon: ''
        },
        updateRole:
            {
              name: 'update',
              title: '修改',
              action: updateRole,
              icon: ''
            },
        deleteRole: {
          name: 'delete',
          title: '删除',
          action: deleteRole,
          icon: ''
        },
        roleBindMent: {
          name: 'roleBindMent',
          title: '关联菜单',
          action: roleBindMent,
          icon: ''
        }
      },
      // 弹窗携带的数据 tag对应action
      dialog: {
        tag: {},
        title: '',
        readonly: false,
        fromData: {
          roleName: ''
        }
      },
      treerOption: {
        id: '',
        name: 'test',
        checkbox: true,
        key: 'id',
        expandedKeys: [],
        checkedKeys: []
      }
    }
  },
  created() {
    this.initRole()
    // 获取名下所有资源信息
    getMenus().then((response) => {
      if (response.data) {
        this.resource = response.data
      }
    })
  },
  methods: {
    // 初始化
    initRole() {
      this.getData()
    },
    // 获取数据
    async getData() {
      const self = this
      // 加载ing
      self.tableLoading = true
      self.dialogMark = false
      await getRoles(self.searchData).then(response => {
        if (response) {
          // 关闭加载
          // 分页数据
          self.page.tableData = response.data
          self.page.size = response.pageSize
          self.page.totalPage = response.totalPage
        }
      }).catch((error) => {
        console.error(error)
      })
      self.tableLoading = false
    },
    // 改变展示条数
    handleSizeChange(val) {
      const self = this
      self.searchData.pageSize = val
      self.initRole()
    },
    // 改变页数
    handleCurrentChange(val) {
      const self = this
      self.searchData.pageNum = val
      self.initRole()
    },
    // 批量操作
    handleBatchOperate() {
      const self = this
      if (self.multitermData.length < 1) {
        self.message('你好歹选一条啊')
        return
      }
      switch (self.operateType) {
        case 1:
          self.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let roleId = ''
            self.multitermData.forEach(e => {
              roleId += e.id + ','
            })
            deleteRole({ roleId: roleId }).then((response) => {
              if (response && response.code === 0) {
                self.$notify.success('操作成功,请刷新界面')
                // 刷新界面
                self.init()
              }
            }).catch((error) => {
              console.error(error)
              self.$message('操作异常')
            })
          }).catch(() => {
          })
          break
        default:
          self.message('未定义操作')
          break
      }
    },
    // 添加
    addRoleSub() {
      const self = this
      // 还原表单状态
      self.dialog = self.$options.data().dialog
      // 带入操作按钮
      self.dialog.tag = self.button.addRole
      // 打开弹窗
      self.dialogSwitch()
    },
    // 删除
    deleteSub(button, scope) {
      const self = this
      const parame = {}
      parame.roleId = scope.row.id
      button.action(parame).then((response) => {
        if (response && response.code === 0) {
          self.$notify.success('操作成功')
          // 刷新界面
          self.initRole()
        }
      }).catch((error) => {
        console.error(error)
        self.$message('操作异常')
      })
    },
    // 修改
    updateRoleSub(button, scope) {
      const self = this
      // 初始化表单状态
      self.dialog = self.$options.data().dialog
      const name = self.$util.copy(scope.row.roleName)
      self.dialog.fromData.roleName = name
      // 带入操作按钮
      self.dialog.tag = button
      // 打开弹窗
      self.dialogSwitch()
    },

    // 查看
    getRoleSub(button, scope) {
      const self = this
      // 判断弹出类型
      self.dialog.readonly = true
      // 表单赋值
      self.dialog.fromData.roleName = scope.row.roleName
      self.dialogSwitch()
    },

    async roleRelMentSub(button, scope) {
      const self = this
      self.dialog.tag = button
      // 获取角色对应资源
      const parame = {
        id: scope.row.id
      }
      self.treerOption.id = scope.row.id
      await mensByRole(parame).then((response) => {
        if (response.data && response.data.length > 0) {
          const checkedKeys = []
          const expandedKeys = []
          for (let i = 0; i < response.data.length; i++) {
            checkedKeys.push(response.data[i].id)
            expandedKeys.push(response.data[i].parentId)
          }
          this.treerOption.checkedKeys = checkedKeys
          this.treerOption.expandedKeys = Array.from(new Set(expandedKeys))
        } else {
          this.treerOption.checkedKeys = []
          this.treerOption.expandedKeys = []
        }
      }
      ).catch()
      // 打开弹窗
      self.mentDialogSwitch()
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 统一处理一般请求
    buttonCommit(button, scope) {
      const self = this
      if (button && button.name) {
        switch (button.name) {
          case 'add':
            self.addRoleSub(button, scope)
            break
          case 'update':
            self.updateRoleSub(button, scope)
            break
          case 'get':
            self.getRoleSub(button, scope)
            break
          case 'delete':
            self.deleteSub(button, scope)
            break
          case 'roleBindMent':
            self.roleRelMentSub(button, scope)
            break
          default:
            self.$message({
              message: '没有找到对应的操作',
              type: 'error'
            })
        }
      }
    },
    // add-update 切换弹窗
    dialogSwitch() {
      const self = this
      self.dialogMark = !self.dialogMark
    },
    // 关联角色 切换弹窗
    mentDialogSwitch() {
      const self = this
      self.mentDialogMark = !self.mentDialogMark
    },

    // 提交
    submitForm(ruleForm, action) {
      const self = this
      self.$refs[ruleForm].validate((valid) => {
        if (valid) {
          action(self.dialog.fromData).then((response) => {
            if (response && response.code === 0) {
              self.$notify.success('操作成功')
              // 刷新界面
              self.initRole()
            }
          }
          ).catch((error) => {
            console.error(error)
            self.$message('操作异常')
          }
          )
        }
      })
    },
    treeSubmit(action) {
      const _self = this
      // 获取key
      const treeKey = _self.$refs.tree.$children[0].getCheckedKeys()
      if (_self.$util.myIsNotEmpty(treeKey)) {
        const param = {
          mendIds: treeKey.toString(),
          roId: _self.treerOption.id
        }
        action(param).then((response) => {
          if (response && response.code === 0) {
            _self.$notify.success('操作成功')
            // 关闭弹窗
            _self.mentDialogMark = false
          }
        }).catch()
      } else {
        _self.$message('给他一个资源吧')
      }
    },
    // 初始化角色菜单
    initMentDialog() {
      const _self = this
      _self.$refs.tree.$children[0].setCheckedKeys([])
      _self.$refs.tree.$children[0].setCheckedKeys(_self.treerOption.checkedKeys)
    }
  }
}
</script>

<style>

  /*行高*/
  .el-table__header tr,
  .el-table__header th {
    padding: 10px;
  }

  .el-table__body tr,
  .el-table__body td {
    padding: 10px;
  }

</style>
