<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

const mid = ref('');
const message = ref('');
const talk = ref(null);
let ws = null;

const connect = () => {
    if (ws) {
        ws.close();
    }
    ws = new WebSocket(`ws://${location.hostname}:8080/chatt`);

    ws.onmessage = (msg) => {
        const data = JSON.parse(msg.data);
        const cssClass = data.mid === mid.value ? 'me' : 'other';
        const item = `
            <div class="${cssClass}">
                <span><b>${data.mid}</b></span> [ ${data.date} ]<br/>
                <span>${data.msg}</span>
            </div>
        `;
        talk.value.innerHTML += item;
        talk.value.scrollTop = talk.value.scrollHeight; // Scroll to the bottom
    };
};

const send = () => {
    if (message.value.trim()) {
        const data = {
            mid: mid.value,
            msg: message.value,
            date: new Date().toLocaleString(),
        };
        ws.send(JSON.stringify(data));
        message.value = ''; // Clear the message input
    }
};

onMounted(() => {
    talk.value = document.getElementById('talk');
});

onUnmounted(() => {
    if (ws) {
        ws.close();
    }
});
</script>
<template>
    <div id="chatt">
        <h1>WebSocket Chatting</h1>
        <input type="text" id="mid" v-model="mid" placeholder="Enter your ID" />
        <input type="button" value="로그인" @click="connect" />
        <br />
        <div id="talk"></div>
        <div id="sendZone">
            <textarea id="msg" v-model="message" @keyup.enter="send"></textarea>
            <input type="button" value="전송" @click="send" />
        </div>
    </div>
</template>

<style scoped>
* {
    box-sizing: border-box;
}

#chatt {
    width: 800px;
    margin: 20px auto;
}

#chatt #talk {
    width: 800px;
    height: 400px;
    overflow: scroll;
    background: #fff;
    border: 1px solid #aaa;
}
#chatt #msg {
    width: 740px;
    height: 100px;
    display: inline-block;
}

#chatt #sendZone > * {
    vertical-align: top;
}
#chatt #btnSend {
    width: 54px;
    height: 100px;
}

#chatt #talk div {
    width: 70%;
    display: inline-block;
    padding: 6px;
    border-radius: 10px;
}

#chatt .me {
    background-color: #ffc;
    margin: 1px 0px 2px 30%;
}

#chatt .other {
    background-color: #eee;
    margin: 2px;
}
</style>
