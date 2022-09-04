import React, {useEffect, useState} from "react";
import {embeddedGet} from "../../api/service/Service";
import {Link} from "react-router-dom";

export const Footer = () => {
    const [aware, setAware] = useState([])

    const getAware = async () => {
        try {
            embeddedGet("aware", setAware, "data")
        } catch (err) {
        }
    }
    useEffect(() => {
        getAware()
    }, [])

    return (
        <div className="col-12 bg-dark d-flex align-items-center justify-content-between" style={{height: '50vh'}}>
            <div className="col-12 col-md-4 col-sm-12 d-flex align-items-center justify-content-center flex-column"
                 style={{height: '100%', borderRight: '2px solid white'}}>
                <Links aware={aware} status={"ABOUT"}/>
            </div>
            <div className="col-12 col-md-4 col-sm-12 d-flex align-items-center justify-content-center flex-column"
                 style={{height: '100%', borderRight: '2px solid white'}}>
                <Links aware={aware} status={"LINKS"}/>
            </div>
            <div className="col-12 col-md-4 col-sm-12 d-flex align-items-center justify-content-center flex-column"
                 style={{height: '100%'}}>
                <Links aware={aware} status={"TARMOQLAR"}/>
            </div>
        </div>
    )
}

export const Links = ({aware, status}) => {
    return (
        aware.map(item => (
            item.awareStatus === status ? (
                status === "ABOUT" ?
                    <Link to={item.link} className="btn text-warning">{item.nameUz}</Link>
                    :
                    status === "TARMOQLAR" ?
                        <a href={item.link} className="text-warning">{item.nameUz}</a>
                        :
                        status === "LINKS" ?
                            <a href={item.link} className="text-warning">{item.nameUz}</a>
                            : ""
            ) : (
                ""
            )
        ))
    )
}