/**
����ļ�
 */
import { createApp } from 'vue'//���ڴ���vueӦ��ʵ��
import App from './App.vue'//�����
//����element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// �����������԰�
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

// import './assets/main.css'//����ȫ��css��ʽ

createApp(App).use(ElementPlus, {locale: zhCn,}).mount('#app')//����vueʵ����������ص�idΪapp��<div>����
