// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// All of the Node.js APIs are available in this process.
const ipc = require("electron").ipcRenderer;

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