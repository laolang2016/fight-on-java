<template>
  <div>
    <van-search v-model="searchValue" placeholder="请输入搜索关键词" disabled shape="round"/>
    <p class="txt">This is a Vue app.</p>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data () {
    return {
      searchValue: ''
    }
  }
}
</script>

<style lang="scss">
.txt{
  font-size: .16rem;
}
</style>
