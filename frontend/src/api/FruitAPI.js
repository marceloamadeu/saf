import axios from 'axios';

//const apiUrl = 'http://10.36.1.49:8080/api/';

const apiUrl = process.env.VUE_APP_ROOT_API

export default {
    getFruits: (callback) => {
        const apiUrlFruits = apiUrl + 'v1/fruits';
        axios.get(apiUrlFruits).then( (fruits) => {
            if (callback) {
                callback(fruits);
           }
        })        
    }
}
