<template>
    <div>
        <div class="headerbox">
            <ChatHeader/>
        </div>
        <!-- <el-divider></el-divider>   -->
        <ChatRoom/>
    </div>
</template>

<script>
import ChatHeader from './ChatHeader'
import ChatRoom from './ChatRoom'
export default {
    components: {
        ChatHeader,
        ChatRoom
    }
}
</script>

<style scoped>
.headerbox {
    /* border-style:solid; */
    border-bottom: 1px solid #C0C4CC;
}
</style>