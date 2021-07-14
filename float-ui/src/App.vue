<template>
  <div id="app">
    <el-input v-model="input" placeholder="请输入内容"></el-input>
    <el-radio v-model="radio" label="1">备选项</el-radio>
    <el-radio v-model="radio" label="2">备选项</el-radio>
    <el-switch
        v-model="value"
        active-color="#13ce66"
        inactive-color="#ff4949">
    </el-switch>

    <el-date-picker
        v-model="value1"
        type="datetime"
        placeholder="选择日期时间">
    </el-date-picker>
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
