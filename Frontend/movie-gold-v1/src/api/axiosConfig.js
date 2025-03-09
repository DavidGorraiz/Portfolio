import axios from 'axios';

export default axios.create({
    baseURL: 'https://9f57-103-219-234-203.ngrok-free.app',
    headers: {"ngrok-skip-browser-warning": "true"}
});