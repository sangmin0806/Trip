<template>
    <div class="my-page">
        <h1>My Page</h1>
        <div class="nav-links">
            <router-link to="/mypage/detail" class="nav-link" active-class="active-link">User Detail</router-link>
            <router-link to="/mypage/edit" class="nav-link" active-class="active-link">Edit User</router-link>
            <router-link to="/mychatt" class="nav-link" active-class="active-link">Edit User</router-link>
        </div>
        <div class="content">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MyPage',
};
</script>

<style scoped>
.my-page {
    max-width: 1200px; /* Increase the maximum width */
    width: 90%; /* Set width to 90% to make it responsive */
    padding: 20px;
    margin: 20px auto; /* Center the container */
    background: #ffffff;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    box-sizing: border-box; /* Ensure padding is included in the width */
}

h1 {
    font-size: 28px;
    margin-bottom: 20px;
    text-align: center;
    color: #333;
}

.nav-links {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.nav-link {
    padding: 10px 20px;
    margin: 0 10px;
    text-decoration: none;
    color: #007bff;
    border: 1px solid #007bff;
    border-radius: 5px;
    transition: background 0.3s, color 0.3s;
}

.nav-link:hover {
    background: #007bff;
    color: #fff;
}

.active-link {
    background: #007bff;
    color: #fff;
}

.content {
    padding: 20px;
    background: #f9f9f9;
    border-radius: 10px;
}
</style>
