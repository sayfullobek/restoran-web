import React, {useEffect, useState} from "react";
import {Admin} from "../pages/admin/Admin";
import {Navbar} from "../component/navbar/Navbar";
import {Outlet, useLocation, useNavigate} from "react-router-dom";
import {Sidebar} from "../component/sidebar/Sidebar";
import {Header} from "../component/header/Header";
import Toaster from "react-hot-toast";
import {ToastContainer} from "react-toastify";
import {isAuthenticated} from "../handlers/auth";

export const AdminLayout = () => {
    const navigate = useNavigate()
    const [toggle, setToggle] = useState(false)
    const [loading, setLoading] = useState(false)
    const role = localStorage.getItem('role')

    const location = useLocation()
    useEffect(() => {
        const redirectAdminPanel = () => {
            const token = localStorage.getItem('token');
            const isAuth = isAuthenticated(token)
            if (!isAuth) return navigate('/')
            setLoading(true)
        }
        redirectAdminPanel()
    })

    const clickToggle = () => {
        setToggle(!toggle)
    }

    return (
        <>
            <Header clickToggle={clickToggle}/>
            <Sidebar clickToggle={clickToggle} toggle={toggle}/>
            <main id='main' className={'main'} style={{marginLeft: toggle && '0'}}>
                {loading ? (
                    <>
                        <Outlet/>
                        <ToastContainer/>
                    </>
                ) : (
                    <div id="loader-container" className='d-flex align-items-center justify-content-center'>
                        <div className="spinner-border">
                            <span className="visually-hidden">Loading...</span>
                        </div>
                    </div>
                )}
            </main>
        </>
    )
}