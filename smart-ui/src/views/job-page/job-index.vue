<template>
  <div style="height: 100%">

    <el-row style="height: 30%">
      <div style="margin:10px 0">
        <el-card>
          <el-row :gutter="2">
            <el-col :xs="24" :sm="24" :md="24" :lg="2" :xl="2">
              <el-input ref="search" v-model="searchData.jobName" style="height: 100%;" placeholder="输入任务名称"/>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
              <div style="padding-left:  2vw">
                <el-button type="primary" @click="getData">查询</el-button>
                <el-button
                  v-for="(item,index ) in btn.topBtn"
                  :key="index"
                  type="primary"
                  @click="buttonCommit(item)">
                  <svg-icon :icon-class="item.icon"/>
                  {{ item.title }}
                </el-button>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </div>

    </el-row>
    <el-row style="height: 70vh">
      <el-col :span="24" style="height: 100%">
        <el-card style="height: 100%">
          <el-table
            v-loading="tableLoading"
            :data="page.tableData"
            element-loading-text="加载中,请稍后"
            stripe
            align="center"
            class="page-table"
            @selection-change="(e)=>{multitermData=e}">
            <el-table-column align="center" type="selection" width="60"/>
            <el-table-column align="center" type="index" label="序号" width="60"/>

            <el-table-column
              align="center"
              prop="jobName"
              label="任务名称"
            />

            <el-table-column
              align="center"
              prop="cronExpression"
              label="cron表达式"
            />
            <el-table-column
              align="center"
              prop="jobGroup"
              label="任务组名"
            />

            <el-table-column
              align="center"
              prop="invokeTarget"
              label="执行目标"
            />
            <el-table-column
              align="center"
              prop="concurrency"
              label="是否运行并发"
            />
            <el-table-column
              align="center"
              prop="status"
              label="状态"
            />
            <el-table-column
              align="center"
              prop="count"
              label="执行次数"
            />
            <el-table-column
              align="center"
              prop="misfirePolicy"
              label="执行策略"
            />
            <el-table-column
              align="center"
              prop="lastTime"
              label="最近一次执行时间"
            />
            <el-table-column
              align="center"
            >
              <el-table-column v-if="btn.buttonHandle.length >0 " label="操作" align="center">
                <template slot-scope="scope">
                  <el-dropdown
                    @command="(e)=>{buttonCommit(e,scope)}"
                  >
                    <span class="el-dropdown-link">
                      <el-button type="primary">功能<i class="el-icon-arrow-down el-icon&#45;&#45;right"/></el-button>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item
                        v-for="(item,index) in btn.buttonHandle"
                        :key="index"
                        :command="item"
                      >
                        <svg-icon :icon-class="item.icon"/>
                        {{ item.title }}
                      </el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </template>
              </el-table-column>

            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-card>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
          <!--批量操作-->
          <div v-if="btn.downBtn.length>0" class="batch-operate-container">
            <el-select
              v-model="operateType"
              size="small"
              placeholder="批量操作">
              <template v-for="item in btn.downBtn">
                <el-option
                  :key="item.title"
                  :label="item.title"
                  :value="item.menuName">
                  <svg-icon :icon-class="item.icon"/>
                  <span>{{ item.title }}</span>
                </el-option>
              </template>
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
        </el-col>
      </el-row>
    </el-card>
    <!--弹窗-->
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
            width="28%"
          >
            <div style="width: 80%">
              <el-form ref="oneFrom" :rules="form.rule" :model="form.one" label-width="100px" size="mini">

                <el-form-item label="任务名称:" prop="jobName">
                  <el-input :disabled="form.one.disabled" v-model="form.one.jobName" class="input"/>
                </el-form-item>

                <el-form-item label="cron表达式:" prop="cronExpression">
                  <el-input :disabled="form.one.disabled" v-model="form.one.cronExpression" class="input"/>
                </el-form-item>

                <el-form-item label="任务组名:" prop="jobGroup">
                  <el-input :disabled="form.one.disabled" v-model="form.one.jobGroup" class="input"/>
                </el-form-item>

                <el-form-item label="目标字符串:" prop="invokeTarget">
                  <el-input :disabled="form.one.disabled" v-model="form.one.invokeTarget" class="input"/>
                </el-form-item>

                <el-form-item label="是否运行并发:" prop="concurrency">
                  <el-input :disabled="form.one.disabled" v-model="form.one.concurrency" class="input"/>
                </el-form-item>
                <el-form-item label="执行策略:" prop="misfirePolicy">
                  <el-input :disabled="form.one.disabled" v-model="form.one.misfirePolicy" class="input"/>
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
      </el-col></el-row>
    </div>
  </div>
</template>

<script>
import { getTaskList, deleteTask, addTask } from '@/api/job.js'
import request from '@/api/base.js'

export default {
  name: 'Job',
  data: function() {
    return {
      dialog: {
        show: {
          fromDialog: false
        },
        title: '',
        submitTo: ''
      },
      multitermData: [],
      operateType: null,
      operates: [
        {
          label: '删除',
          value: 1
        }
      ],
      form: {
        one: {
          jobId: '',
          jobName: '',
          cronExpression: '',
          jobGroup: '',
          invokeTarget: '',
          concurrency: '',
          status: '',
          count: '',
          lastTime: '',
          misfirePolicy: '',
          disabled: false
        },
        rule: {
          username: [
            { required: true, message: '用户名称必填', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码必填', trigger: 'change' },
            { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' }
          ]
        }
      },
      tableLoading: false,
      filterText: '',
      tree: {
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
        jobName: '',
        pageNum: 1,
        pageSize: 10
      },
      // 不需要每行都生成
      add: {
        name: 'add',
        title: '添加',
        action: addTask,
        icon: ''
      },
      btnArray: [],
      btn: {
        // 上面的按钮
        topBtn: [],
        // 右侧的按钮
        buttonHandle: [],
        // 左下的按钮
        downBtn: []
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    // 初始化
    /** ****************表单数据初始化start******************************/
    init: async function() {
      const self = this
      // 加载表单标识
      self.tableLoading = true
      // 请求数据
      await getTaskList(self.searchData).then((response) => {
        self.page.tableData = response.data
        self.page.size = response.pageSize
        self.page.totalPage = response.totalPage
      }).catch(() => {
        self.tableLoading = false
      })
      self.tableLoading = false
      /** ****************表单数据初始化end******************************/
      /** ****************生成每行的按钮start******************************/
      // 解析按钮
      const resource = self.$store.getters.resource
      // 从vuew中获取不用在请求一次
      // 上面的按钮
      const topBtn = resource.filter(e => (('/' + e.parentUrl + '/index') === self.$route.path || ('/' + e.parentUrl) === self.$route.path) &&
          e.type === '按钮' && e.btnType === '上面')
      self.btn.topBtn = topBtn

      // 右面的按钮
      const buttonHandle = resource.filter(e => (('/' + e.parentUrl + '/index') === self.$route.path || ('/' + e.parentUrl) === self.$route.path) &&
          e.type === '按钮' && e.btnType === '右面')
      self.btn.buttonHandle = buttonHandle

      // 下方按钮
      const downBtn = resource.filter(e => (('/' + e.parentUrl + '/index') === self.$route.path || ('/' + e.parentUrl) === self.$route.path) &&
          e.type === '按钮' && e.btnType === '下面')
      self.btn.downBtn = downBtn
      /** ****************生成每行的按钮end******************************/
    },
    async getData() {
      const self = this
      self.tableLoading = true
      await getTaskList(self.searchData).then((response) => {
        self.page.tableData = response.data
        self.page.size = response.pageSize
        self.page.totalPage = response.totalPage
      }).catch((e) => {
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
          self.$confirm('此操作将删除多条用户数据请谨慎操作, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let userIds = ''
            self.multitermData.forEach(e => {
              userIds += e.id + ','
            })
            deleteTask({ userIds: userIds }).then((response) => {
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
    /** *******************响应的操作start*******************/
    addTask() {
      const self = this
      // 还原表单状态
      self.form.one = self.$options.data().form.one
      // 带入url
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
      self.dialog.submitTo = button.url
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
        request.post(button.url, parame).then((response) => {
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
    resetPassword(button, scope) {
      request.get(button.url, { userId: scope.row.id }).then((e) => {
        this.$notify.success('操作成功')
      }).catch(() => {
      })
    },

    // 统一处理一般请求
    buttonCommit(button, scope) {
      const self = this
      if (button && button.menuName) {
        switch (button.menuName) {
          case 'add':
            self.addTask(button, scope)
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
          case 'resetPassword':
            self.resetPassword(button, scope)
            break
          default:
            self.$message({
              message: '没有找到对应的操作',
              type: 'error'
            })
        }
      }
    },
    /** *******************响应的操作end*******************/
    // 提交
    submitForm(formName, button) {
      const self = this
      self.$refs[formName].validate((valid) => {
        if (valid) {
          request.post(button.url, self.form.one).then((response) => {
            if (response && response.code === 0) {
              self.$notify.success('操作成功')
              self.dialog.show.fromDialog = false
              self.getData()
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
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    }
  }
}
</script>

<style scoped>

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
