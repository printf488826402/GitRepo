/**
入口文件
 */
import { createApp } from 'vue'//用于创建vue应用实例
import App from './App.vue'//根组件
//引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 引入中文语言包
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

// import './assets/main.css'//引入全局css样式

createApp(App).use(ElementPlus, {locale: zhCn,}).mount('#app')//创建vue实例，将其挂载到id为app的<div>区域
