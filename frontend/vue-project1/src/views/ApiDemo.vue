<script setup>
//引入ref函数
import { ref, onMounted } from 'vue';
//申明响应式数据(对象)
const count = ref(0);
//定义函数//组合式api中没有this
function increament() {
    count.value++;
}
//钩子函数
onMounted(() => {
    console.log('Vue mounted');
})
</script>
<template>
    <button v-on:click="increament">count:{{ count }}</button>
</template>
<style scoped>
</style>