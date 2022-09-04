import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from "react-router-dom"
import {isAuthenticated} from '../handlers/auth';
import {ToastContainer} from 'react-toastify';
import {login} from "../api/service/AuthService";

export const Login = () => {
    const navigate = useNavigate()
    const [phoneNumber, setPhoneNumber] = useState('')
    const [password, setPassword] = useState('')

    useEffect(() => {
        const redirectAdminPanel = () => {
            const token = localStorage.getItem('token');
            const isAuth = isAuthenticated(token)
            if (isAuth) return navigate('/admin')
        }
        redirectAdminPanel()
    }, [])

    const loginHandler = async (e) => {
        e.preventDefault()

        const params = {
            phoneNumber,
            password
        }
        await login(params)
    }
    return (
        <div style={{top:'260px'}} className='position-relative d-flex align-items-center justify-content-center' id='login-container'>
            <div className="container">
                <div className="row">
                    <div className="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-sm-10 offset-sm-1 col-12">
                        <form onSubmit={loginHandler} className="card border-top border-5 border-primary">
                            <div className="card-header text-center">
                                <h1 className='fw-bold text-dark'>restaran login</h1>
                            </div>
                            <div className="card-body">
                                <div className='mb-3'>
                                    <label htmlFor="phoneNumber" className='mb-2 fw-bold'>PhoneNumber</label>
                                    <input type="text" className='form-control' id='phoneNumber'
                                           placeholder='phoneNumber' autoComplete='off' value={phoneNumber}
                                           onChange={e => setPhoneNumber(e.target.value)}/>
                                </div>
                                <div className='mb-3'>
                                    <label htmlFor="password" className='mb-2 fw-bold'>Password</label>
                                    <input type="password" className='form-control' id='password' placeholder='password'
                                           autoComplete='off' value={password}
                                           onChange={e => setPassword(e.target.value)}/>
                                </div>
                            </div>
                            <div className="card-footer col-12 d-flex align-items-center justify-content-between">
                                <div>
                                    <Link className="btn btn-success" style={{margin: '0 10px'}}
                                          to={"/auth/register"}>register</Link>
                                    <Link className="btn btn-success" to={"/"}>asosiy menyuga qaytish</Link>
                                </div>
                                <button className='btn btn-success btn-block'>
                                    Kirish
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <ToastContainer/>
        </div>
    )
}