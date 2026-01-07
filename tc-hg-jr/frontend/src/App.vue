<template>
  <div class="app-container">
    <header class="app-header">
      <h1>JR Express 预订系统</h1>
    </header>
    <main>
      <SearchResults />
    </main>
  </div>
</template>

<script setup>
import SearchResults from './views/SearchResults.vue'
</script>

<style>
body {
  margin: 0;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  background-color: #f5f7fa;
}
.app-header {
  background-color: #000;
  color: white;
  padding: 1rem;
  text-align: center;
}
</style>