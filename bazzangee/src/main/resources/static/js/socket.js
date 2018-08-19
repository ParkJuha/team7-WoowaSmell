document.addEventListener("DOMContentLoaded", function() {
    WebSocket.init();
});

let WebSocket = (function() {
    const SERVER_SOCKET_API = "/websockethandler";
    const ENTER_KEY = 13;
    let stompClient;
    let textArea = document.querySelector(".chat-text-box");
    let inputElm = document.querySelector(".chat-input-area");

    function connect() {
        let socket = new SockJS(SERVER_SOCKET_API);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function() {
            stompClient.subscribe('/topic/roomId', function(msg) {
                printMessage(JSON.parse(msg.body).body);
            });
        });
    }

    function printMessage({contents, writtenTime}) {
        const parsedTime = parseWrittenTime(writtenTime);
        const chatHtml = `<div class="chat-contents-time">
                            <div><span class="chat-contents">${contents}</span>
                                 <span class="chat-time">${parsedTime}</span>
                            </div>
                          </div>`;
        textArea.insertAdjacentHTML("beforeend", chatHtml);
        textArea.scrollTop = textArea.scrollHeight;
    }

    function parseWrittenTime(dateTime) {
        const time = dateTime.split('T')[1].split(':');
        return time[0] + ':' + time[1];
    }

    function chatKeyDownHandler(e) {
        if(e.which === ENTER_KEY && inputElm.value.trim() !== '') {
            sendMessage(inputElm.value);
            clear(inputElm);
        }
    }

    function clear(input) {
        input.value = '';
    }

    function sendMessage(text) {
        stompClient.send("/app/hello", {}, JSON.stringify({'contents' : text}));
    }

    function init() {
        connect();
        inputElm.addEventListener("keydown", chatKeyDownHandler);
    }

    return {
        init : init
    }
})();