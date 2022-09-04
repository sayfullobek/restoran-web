import React, {useEffect, useState} from "react";
import {Link, Outlet, useNavigate} from "react-router-dom";
import '../../styles/css/admin.css'
import '../../styles/js/admin'
import {embeddedGet} from "../../api/service/Service";

export const Admin = () => {
    const [category, setCategory] = useState([])
    const [product, setProduct] = useState([])
    const [aware, setAware] = useState([])
    const [user, setUser] = useState([])

    const getAll = async () => {
        try {
            embeddedGet("category", setCategory, "embedded")
            embeddedGet("product", setProduct, "data")
            embeddedGet("aware", setAware, "data")
            embeddedGet("auth", setUser, "data")
        } catch (err) {
        }
    }

    useEffect(()=>{
        getAll()
    }, [])

    return (
        <div className="row">
            <Cards title={"category"} size={category.length} about={"kategoriya bu asosiy malumot"} link={"/admin/category"} linkName={"category"}/>
            <Cards title={"product"} size={product.length} about={"product bu asosiy malumot"} link={"/admin/product"} linkName={"product"}/>
            <Cards title={"aware"} size={aware.length} about={"aware bu asosiy malumot"} link={"/admin/aware"} linkName={"aware"}/>
            <Cards title={"user"} size={user.length} about={"user bu asosiy malumot"} link={"null"} linkName={"user"}/>
        </div>
    )
}

export const Cards = ({title, size, about, link, linkName}) => {
    return (
        <div className="card col-12 col-md-5 col-sm-5 m-5 mt-2">
            <h5 className="card-header">{title}</h5>
            <div className="card-body">
                <h1 className="card-title" style={{fontSize:'50px'}}>{size}</h1>
                <p className="card-text">{about}</p>
                {link!=="null"?
                    <Link to={link} className="btn btn-success">{linkName}</Link>
                    :
                    <Link to={"/admin"} className="btn btn-success">{linkName}</Link>
                }
            </div>
        </div>
    )
}