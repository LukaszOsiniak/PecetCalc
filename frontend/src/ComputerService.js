import axios from 'axios'

const COMPUTERS_REST_API_URL = 'http://localhost:8080/computers';

class ComputerService {

    getComputers(){
        return axios.get(COMPUTERS_REST_API_URL);
    }
}

export default new ComputerService();

