import CinemaAxios from '../apis/CinemaAxios'
import {jwtDecode} from "jwt-decode"

export const login = async function(username, password){
    const cred = {
        username: username,
        password: password
    }

    try{
        const ret = await CinemaAxios.post('korisnici/auth',cred);
        const jwt_decoded = jwtDecode(ret.data);
        window.localStorage.setItem('jwt',ret.data);
        window.localStorage.setItem('role',jwt_decoded.role.authority);
        // window.location.replace("http://localhost:3000")
        window.location.reload();
    }catch(err){
        alert("Neuspesan login");
        console.log(err);
    }
}

export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.localStorage.removeItem('role');
    window.location.reload();
}
