<template>
  <div>
    <div class="login-container">
      <!--背景粒子动画-->
      <!--<vueCanvasNest ref="nest" :config="config" :el="'#particles-js'"/>-->
      <Snow ref="snow" />
      <div
        v-if="isShowLoadIcon"
        style="
          width: 100%;
          height: 100px;
          padding-top: 50px;
          position: absolute;
        "
      >
        <div id="loginMark" class="login-mark">
          <img :src="img" style="width: 100%; height: 100%; margin: auto 0" >
        </div>
      </div>
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form animated bounceInDown"
        auto-complete="on"
        label-position="left"
      >
        <h3 class="title">smartoauth2</h3>
        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            v-model="loginForm.username"
            name="username"
            type="text"
            auto-complete="on"
            placeholder="username"
          />
        </el-form-item>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :type="pwdType"
            v-model="loginForm.password"
            name="password"
            auto-complete="on"
            placeholder="password"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon
              :icon-class="pwdType === 'password' ? 'eye' : 'eye-open'"
            />
          </span>
        </el-form-item>
        <el-form-item ref="btn_ref">
          <div class="btuCls" @click="formError = 0">
            <ParticleEffectButton
              ref="btn_submit"
              :visible.sync="btnOption.visible"
              :animating.sync="btnOption.animating"
              :options="btnOption"
            >
              <span class="btn_span">登陆</span>
            </ParticleEffectButton>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import Snow from '@/components/Snow'
import ParticleEffectButton from 'vue-particle-effect-buttons'
import { oauthLogin } from '@/api/user'
export default {
  name: 'Login',
  components: { Snow, ParticleEffectButton },
  data() {
    const self = this
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      tt: { dialogVisible: false },
      img: require('@/assets/loading.gif'),
      isShowLoadIcon: false,
      loginForm: {
        username: 'root',
        password: '123'
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', validator: validateUsername }
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePass }
        ]
      },
      pwdType: 'password',
      // 用来控制onComplete防止无限循环
      isLoginSucess: 0,
      btnOption: {
        /* 動畫時間*/
        duration: 1000,
        /* 風格*/
        easing: 'easeInOutCubic',
        /* 粒子密度*/
        particlesAmountCoefficient: 2,
        /* 分散曲率*/
        oscillationCoefficient: 1,
        onComplete: () => {
          self.isLoginSucess += 1
          if (self.isLoginSucess % 2 === 1) {
            self.handleLogin()
          }
        },
        onBegin: () => {},
        animating: false,
        visible: true
      }
    }
  },
  mounted() {
    const self = this
    self.$refs.snow.$el.style.backgroundImage = 'url(https://cdn.jsdelivr.net/gh/axiaoxiaosu/imgResource@main/snow/login-brackground.jpg)'
    self.$refs.btn_ref.$el.style.backgroundColor = 'rgb(0,0,0,0)'
    self.$refs.btn_ref.$el.style.borderColor = 'rgb(0,0,0,0)'

    document.onkeydown = function(e) {
      const key = window.event.keyCode
      if (key === 13) {
        // self.formError = 0
        //  self.btnOption.visible = !self.btnOption.visible
        // self.btnOption.animating = !self.btnOption.animating
        self.$refs['btn_submit'].$el.children[0].click()
      }
    }
  },
  methods: {
    showPwd() {
      const self = this
      if (self.pwdType === 'password') {
        self.pwdType = 'text'
      } else {
        self.pwdType = 'password'
      }
    },
    changeIcon(flag) {
      this.btnOption.visible = flag
      this.isShowLoadIcon = !flag
    },
    handleLogin() {
      const _self = this
      this.$refs['loginForm'].validate(async(valid) => {
        if (valid) {
          const formData = new FormData()
          formData.append('username', _self.loginForm.username)
          formData.append('password', _self.loginForm.password)
          window.sessionStorage.setItem('userLogin', false)
          const response = await oauthLogin(formData)
          if (response.code === 200) {
            window.sessionStorage.setItem('userLogin', true)
            this.changeIcon(false)
            window.location.href = `${window.location.origin}/smart/#/home`
          } else {
            window.sessionStorage.setItem('userLogin', false)
            this.changeIcon(true)
          }
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$dark_gray: #889aa4;
$light_gray: #eee;
/* reset element-ui css */
.login-container {
    position: fixed;
  height: 100%;
  width: 100%;
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;

      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }

  .particles {
    position: absolute;
    top: 0;
    width: 100%;
  }

  .vue-particle-effect-button {
    width: 100%;

    .particles-wrapper {
      width: 100%;
    }
  }

  .vue-particle-effect-button .particles-button {
    height: 70px;
    text-align: center;
    width: 100%;
    background: #66b1ff;
    border-radius: 5px;
    border: 0;
  }

  .btn_span {
    position: absolute;
    top: 20%;
  }

   .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  #particles-js {
    position: absolute;
    width: 100%;
    height: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 50%;
  }
  .login-mark {
    width: 60px;
    height: 60px;
    margin: 0 auto;
  }
}
</style>
