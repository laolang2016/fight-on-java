<template>
  <div>
    <van-search v-model="searchValue" placeholder="请输入搜索关键词" disabled shape="round"/>
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
