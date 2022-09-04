import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {Nav} from "reactstrap";
import {Navbar} from "../../component/navbar/Navbar";
import {embeddedGet} from "../../api/service/Service";
import {MenyuJon} from "./MenyuJon";

export const Home = ({search ,setSearch}) => {

    return (
        <>
            <div className="head col-12 d-flex align-items-center justify-content-center">
                <div className="col-8 col-md-4 col-sm-6 d-flex align-items-end justify-content-center">
                    <input type="search" className="form-control-plaintext" placeholder="qidirish... " style={{
                        color: 'black',
                        fontWeight: 'bold',
                        fontSize: '24px',
                        border: '2px solid white',
                        borderRadius: '26px',
                        backgroundColor: 'rgba(250,250,250,.6)'
                    }}
                           value={search} onChange={e => setSearch(e.target.value)}/>
                </div>
            </div>
        </>
    )
}