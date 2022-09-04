import React from "react";
import {Navbar} from "../component/navbar/Navbar";
import {Home} from "../pages/home/Home";
import {MenyuJon} from "../pages/home/MenyuJon";
import {Outlet} from "react-router-dom";
import {Footer} from "../component/footer/Footer";
import {Login} from "../pages/Login";

export const AuthLayout = ()=>{
    return(
        <>
            <Navbar/>
            <Outlet/>
            <Footer/>
        </>
    )
}