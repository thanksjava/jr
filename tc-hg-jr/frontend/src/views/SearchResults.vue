<template>
  <div class="search-page">
    <el-button @click="handleSearch" type="primary">查询日本车票</el-button>

    <el-table :data="trips" v-loading="loading">
      <el-table-column prop="trainNumber" label="车次" />
      <el-table-column prop="depTime" label="发车时间" />
      <el-table-column prop="finalPrice" label="最终价格 (JPY)" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="book(scope.row)">预订</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const trips = ref([])
const loading = ref(false)

const handleSearch = async () => {
  loading.value = true
  try {
    // 调用后端：GET /api/v3/trips/search?userLevel=VIP
    const response = await axios.get('/api/v3/trips/search', {
      params: { userLevel: 'VIP' }
    })
    trips.value = response.data // 自动映射后端 TripInfo 列表
  } catch (error) {
    console.error("搜索失败", error)
  } finally {
    loading.value = false
  }
}
</script>