<template>
  <div style="padding-top: 10px">
    <el-row :gutter="20" style="margin-right: 0">
      <el-col :xs="24" :sm="24" :md="24" :lg="4" :xl="4" style="height: 80vh">
        <div style="height: 100%">
          <el-card style="height: 100%">
            <div class="tree">
              <el-input
                v-model="filterText"
                placeholder="输入关键字进行过滤"/>
              <el-tree
                ref="tree"
                :filter-node-method="filterNode"
                :data="tree.treeData"
                default-expand-all/>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col v-if="device!=='mobile'" :span="1" style="height: 85vh">
        <div style="text-align: center">
          <el-tooltip placement="top">
            <div slot="content">树形结构展示图</div>
            <i
              class="el-icon-d-arrow-left"
              style="   position: absolute;    top: 50%;    transform: translate(0, -50%);"/>
          </el-tooltip>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="19" :xl="19" style="height: 85vh">
        <div class="page-main">
          <el-row style="height: 100%">

            <el-col>
              <el-card style="height: 100%">
                <el-row>
                  <el-col :xs="24" :sm="24" :md="24" :lg="3" :xl="3">
                    <el-input ref="search" v-model="searchData.title" placeholder="输入菜单名称"/>
                  </el-col>
                  <el-col :xs="24" :sm="24" :md="24" :lg="3" :xl="3">
                    <el-button type="primary" @click="getData">查询</el-button>
                    <el-button type="primary" @click="addMenu">添加</el-button>
                  </el-col>
                  <el-col :xs="24" :sm="24" :md="24" :lg="4" :xl="4">
                    <el-switch
                      v-model="showResult"
                      active-text="基本展示"
                      inactive-text="树形展示"
                      @change="switchChange"/>
                  </el-col>
                  <el-col :xs="24" :sm="24" :md="24" :lg="5" :xl="5">
                    <el-tooltip class="item" effect="dark" content="筛选条件" placement="right">
                      <el-checkbox-group v-model="searchData.types" style="float: right;margin-left: 2vw">
                        <el-checkbox v-for="(item,index) in reourceType" :label="item.value" :key="index+item.lable">{{
                        item.lable }}
                        </el-checkbox>
                      </el-checkbox-group>
                    </el-tooltip>
                  </el-col>

                </el-row>
              </el-card>
            </el-col>

          </el-row>
          <el-row style="height: 60vh">
            <el-col style="height: 100%">
              <el-table
                v-loading="tableLoading"
                :data="page.tableData"
                element-loading-text="加载中,请稍后"
                stripe
                align="center"
                class="page-table"
                @selection-change="(e)=>{multitermData=e}">
                <el-table-column align="center" type="selection" width="60"/>
                <el-table-column type="index" label="序号" width="60"/>
                <el-table-tree-column
                  v-if="!showResult"
                  file-icon="icon icon-file"
                  folder-icon="icon icon-folder"
                  prop="menuName"
                  tree-key="id"
                  align="center"
                  parent-key="parentId"
                  level-key="level"
                  label="呜啦呜啦"
                  child-key="children"
                  width="100"
                />

                <el-table-column
                  align="center"
                  prop="type"
                  label="目录"
                />

                <el-table-column
                  align="center"
                  prop="title"
                  label="菜单标题"
                />

                <el-table-column
                  align="center"
                  prop="menuName"
                  label="菜单名称"
                />

                <el-table-column
                  align="center"
                  prop="parentName"
                  label="上级菜单"
                />
                <el-table-column
                  align="center"
                  prop="isShow"
                  label="是否显示"
                />
                <el-table-column
                  show-overflow-tooltip
                  align="center"
                  prop="icon"
                  label="图标"
                >
                  <svg-icon slot-scope="scope" :icon-class="scope.row.icon||'xigua'"/>
                </el-table-column>
                <el-table-column
                  show-overflow-tooltip
                  align="center"
                  sortable
                  prop="createTime"
                  label="创建时间"
                />
                <el-table-column
                  align="center"
                  sortable
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
                          <el-button type="primary">功能<i
                            class="el-icon-arrow-down el-icon&#45;&#45;right"/></el-button>
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
            </el-col>
          </el-row>
          <el-row style="height: 15vh;margin-top: 2%">
            <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
              <!--批量操作-->
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
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
              <!--分页栏-->
              <div v-if="showResult" class="pagination-container">
                <el-pagination
                  :page-size="page.size"
                  :total="page.totalPage"
                  :pager-count="5"
                  background
                  layout="total, sizes, prev, pager, next, jumper"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"/>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <!--dialog-->
    <div>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="19" :xl="19">
          <el-dialog
            v-dialogDrag
            :close-on-click-modal="false"
            :close-on-press-escape="false"
            :visible.sync="dialog.show.fromDialog"
            :title="dialog.title"
            center
            style="width: 100%"
            @open="initdialog"
          >
            <div style="width: 100%">
              <el-form ref="oneFrom" :rules="form.rule" :model="form.one" size="mini">
                <el-form-item label="菜单标题:" prop="title">
                  <el-input :disabled="form.one.disabled" v-model="form.one.title" class="input"/>
                </el-form-item>
                <el-form-item label="菜单名称:" prop="menuName">
                  <el-input :disabled="form.one.disabled" v-model="form.one.menuName" class="input"/>
                </el-form-item>
                <el-form-item label="菜单类型:" prop="type">
                  <el-select
                    :disabled="form.one.disabled"
                    v-model="form.one.type"
                    placeholder="选择资源类型"
                    @change="menuChange">
                    <el-option label="目录" value="1"/>
                    <el-option label="资源" value="2"/>
                    <el-option label="按钮" value="3"/>
                  </el-select>
                </el-form-item>
                <el-form-item label="上级菜单:" prop="parentId">
                  <el-select :disabled="form.one.disabled" v-model="form.one.parentId" placeholder="选择上级菜单">
                    <el-option
                      v-for="(e,index) in page.menuModule"
                      :key="e.title+index"
                      :label="e.title"
                      :value="e.id"/>
                  </el-select>
                </el-form-item>
                <el-form-item label="是否隐藏:">
                  <el-switch
                    :disabled="form.one.disabled"
                    v-model="form.one.isShow"
                    active-value="2"
                    inactive-value="1"/>
                </el-form-item>
                <el-form-item label="跳转路由:">
                  <el-input :disabled="form.one.disabled" v-model="form.one.url" class="input"/>
                </el-form-item>
                <el-form-item label="icon(默认西瓜)">
                  <el-select v-model="form.one.icon" :disabled="form.one.disabled">
                    <el-option
                      v-for="(e,index) in svgData"
                      :key="e+index"
                      :label="e"
                      :value="e"/>
                  </el-select>
                </el-form-item>

                <el-form-item label="按钮出现位置">
                  <el-select v-model="form.one.btnType" :disabled="form.one.disabled">
                    <el-option label="上面" value="1"/>
                    <el-option label="右面" value="2"/>
                    <el-option label="下面" value="3"/>
                  </el-select>
                </el-form-item>
                <el-form-item label="显示顺序">
                  <el-input :disabled="form.one.disabled" v-model="form.one.sort" class="input"/>
                </el-form-item>
                <el-form-item s label="备注">
                  <el-input :disabled="form.one.disabled" v-model="form.one.ramark" class="input"/>
                </el-form-item>
                <el-footer size="large" style="text-align:right">
                  <div v-if="form.one.disabled">
                    <el-button type="primary" @click="()=>{dialog.show=false}">取消</el-button>
                  </div>
                  <div v-else>
                    <el-button type="primary" @click="submitForm('oneFrom',dialog.submitTo)">提交</el-button>
                    <el-button @click="resetForm('oneFrom')">重置</el-button>
                  </div>
                </el-footer>
              </el-form>
            </div>
          </el-dialog>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { addMenu, deleteMenu, updateMenu, getMenusPage } from '@/api/ment'
import ElTableTreeColumn from 'element-tree-grid'
import svgIcons from '../menu/svg-icons'

export default {
  name: 'Menu',
  components: { ElTableTreeColumn },
  data: function() {
    return {
      showResult: true,
      dialog: {
        show: {
          fromDialog: false
        },
        title: '',
        submitTo: {}
      },
      reourceType: [{
        lable: '目录',
        value: '1'
      }, {
        lable: '资源',
        value: '2'
      }, {
        lable: '按钮',
        value: '3'
      }
      ],
      multitermData: [],
      operateType: null,
      svgData: svgIcons,
      operates: [
        {
          label: '删除',
          value: 1
        }
      ],
      form: {
        one: {
          disabled: false,
          title: '',
          type: '',
          isShow: 1,
          parentId: '',
          url: '',
          icon: '',
          sort: '',
          ramark: '',
          routerList: {},
          menusIds: [],
          menuName: '',
          btnType: '2',
          id: ''
        },
        rule: {
          title: [
            { required: true, message: '资源标题必填', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
          ],
          menuName: [
            { required: true, message: '资源名称必填', trigger: 'blur' },
            { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '请选择菜单类型', trigger: 'change' }
          ]
        }
      },
      tableLoading: false,
      filterText: '',
      tree: {
        allData: [],
        treeData: [],
        filterNode: []

      },
      page: {
        tableData: [],
        menuModule: [],
        size: 0,
        totalPage: 0
      },
      searchData: {
        title: '',
        types: [],
        pageNum: 1,
        pageSize: 10
      },
      // 不需要每行都生成
      add: {
        name: 'add',
        title: '添加',
        action: addMenu,
        icon: ''
      },
      // 每一行的操作按钮  name必填   title 必填    action 向服务器提交 icon 图标
      buttonHandle: {
        get: {
          name: 'get',
          title: '查看',
          icon: ''
        },
        update: {
          name: 'update',
          title: '修改',
          action: updateMenu,
          icon: ''
        },
        delete: {
          name: 'delete',
          title: '删除',
          action: deleteMenu,
          icon: ''
        }
      }

    }
  },
  computed: {
    device() {
      return this.$store.state.app.device
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  async created() {
    this.init()
  },
  methods: {
    /* -----------------模版---------------*/
    // 初始化
    async init() {
      const self = this
      self.getData()
      await getMenusPage().then((response) => {
        self.allData = response.data
        self.tree.treeData = self.$util.treeDispose(response.data)
        self.page.menuModule = response.data.filter(e => e.type === '目录')
      })
    },
    async getData() {
      const self = this
      self.showResult = true
      self.tableLoading = true
      // 加载ing
      let getRolesRequest = Object.assign({}, self.searchData)
      getRolesRequest = JSON.parse(JSON.stringify(getRolesRequest))
      await getMenusPage(getRolesRequest).then((response) => {
        if (response) {
          self.page.tableData = response.data
          self.page.size = response.pageSize
          self.page.totalPage = response.totalPage
        }
      }).catch(() => {
        self.tableLoading = false
      })
      self.tableLoading = false
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
            let menusIds = ''
            self.multitermData.forEach(e => {
              menusIds += e.id + ','
            })
            deleteMenu({ menusIds: menusIds }).then((response) => {
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
    addMenu() {
      const self = this
      // 还原表单状态
      self.form.one = self.$options.data().form.one
      // 带入action
      self.dialog.submitTo = self.$data.add
      // 添加dialog标题
      self.dialog.title = '添加'
      // 打开弹窗
      self.dialog.show.fromDialog = true
    },
    update(button, scope) {
      const self = this
      // 还原表单状态
      self.dialog = self.$options.data().dialog
      // 带入该行数据
      const row = self.$util.copy(scope.row)
      self.form.one = row
      // 带入数据id标识
      self.form.menuId = scope.row.id
      // 带入操作按钮
      self.dialog.submitTo = button
      // 打开弹窗
      self.dialog.show.fromDialog = true
    },
    get(button, scope) {
      const self = this
      // 表单赋值
      self.form.one = scope.row
      // 设置只读
      self.form.one.disabled = true
      // 设置dialog文本
      self.dialog.title = '查看'
      // 打开dialog
      self.dialog.show.fromDialog = true
    },
    delete(button, scope) {
      const self = this
      self.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const parame = {}
        parame.menusIds = scope.row.id
        button.action(parame).then((response) => {
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
    },
    // 表单提交
    submitForm(formName, button) {
      const self = this
      self.$refs[formName].validate((valid) => {
        if (valid) {
          button.action(self.form.one).then((response) => {
            if (response && response.code === 0) {
              let msg = '操作成功'
              if (button.name === 'add') msg = '操作成功,请刷新界面查看左侧目录'
              self.$notify.success(msg)
              self.dialog.show.fromDialog = false
              self.init()
            }
          }
          ).catch((error) => {
            console.error(error)
            self.$message('操作异常')
          }
          )
        } else {
          // 验证未通过
          return false
        }
      })
    },
    /* */
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 改变展示条数
    handleSizeChange(val) {
      const self = this
      self.searchData.pageSize = val
      self.init()
    },
    // 改变页数
    handleCurrentChange(val) {
      const self = this
      self.searchData.pageNum = val
      self.init()
    }, // 统一处理一般请求
    buttonCommit(button, scope) {
      const self = this
      if (button && button.name) {
        switch (button.name) {
          case 'add':
            self.add(button, scope)
            break
          case 'update':
            self.update(button, scope)
            break
          case 'get':
            self.get(button, scope)
            break
          case 'delete':
            self.delete(button, scope)
            break
          default:
            self.$message({
              message: '没有找到对应的操作',
              type: 'error'
            })
        }
      }
    },
    // 初始化dialog内部的框框
    initdialog(e) {
      // 初始化上级菜单栏
      this.menuModuleDispole(this.form.one.type)
    },
    menuChange(e) {
      this.menuModuleDispole(e)
    },
    menuModuleDispole(e) {
      const self = this
      if (e === '3' || e === '按钮') {
        const menuArray = self.allData.filter(e => e.type === '菜单')
        self.page.menuModule = menuArray
      } else {
        const menuArray = self.allData.filter(e => e.type === '目录')
        menuArray.push({
          title: '老子自己就是根目录',
          id: 0
        })
        self.page.menuModule = menuArray
      }
    },
    /* 过滤tree内容*/
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 表单展示类型切换
    switchChange(e) {
      const self = this
      if (e) {
        self.getData()
      } else {
        self.page.tableData = self.tree.treeData
      }
    }
  }
  /* -----------------模版---------------*/
}
</script>

<style scoped>

  .page-main {
    margin: auto;
  }

  .page-table {
    margin-top: 1%;
    height: 100%;
    overflow: auto;
  }

  .page-pagination {
    margin-top: 1%;
    text-align: right;
  }

  /*行高*/
  .el-table__header tr,
  .el-table__header th {
    padding: 10px;
  }

  .input {
    width: 60%;
  }

  .el-table__body tr,
  .el-table__body td {
    padding: 10px;
  }

  .tree {
    height: 100%;
  }
</style>
