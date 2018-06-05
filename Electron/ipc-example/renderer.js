// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// All of the Node.js APIs are available in this process.
const ipc = require("electron").ipcRenderer;

// *************************
// SYNCHRONOUS MESSAGGING
// *************************
const syncMsgBtn = document.getElementById("sendSyncMsgBtn");

const syncReply = document.getElementById("syncReply");

syncMsgBtn.addEventListener("click", function() {
    syncReply.innerHTML = "Waiting for response";
    const reply = ipc.sendSync(
        "synchronous-message",
        "Mr. Watson, come here.",
        "I AM A RENDER PROCESS"
    );
    console.log(reply);
    const message = `Synchronous message reply: ${reply}`;
    syncReply.innerHTML = message;
});

// *************************
// ASYNCHRONOUS MESSAGGING
// *************************
const asyncMsgBtn = document.getElementById("sendAsyncMsgBtn");
const asyncReply = document.getElementById("asyncReply");

asyncMsgBtn.addEventListener("click", function() {
    ipc.send("asynchronous-message", "That’s one small step for man");
    asyncReply.innerHTML = "That’s one small step for man";
});

ipc.on("asynchronous-reply", function(event, arg) {
    const message = `Asynchronous message reply: ${arg}`;
    console.log(message);
    asyncReply.innerHTML = asyncReply.innerHTML + arg;
});