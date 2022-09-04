import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {Loader} from "../../component/loader/Loader";
import {awareService, embeddedGet} from "../../api/service/Service";
import data from "bootstrap/js/src/dom/data";

export const MenyuJon = ({filter, search}) => {
    const [category, setCategory] = useState([])
    const [product, setProduct] = useState([])
    const [loading, setLoading] = useState(false)
    const [productByCategory, setProductByCategory] = useState([])
    const storage = localStorage;

    const getCategorys = async () => {
        try {
            await embeddedGet("category", setCategory, "embedded")
            setLoading(true)
        } catch (err) {
        }
    }
    const getProducts = async () => {
        try {
            await embeddedGet("product", setProduct, "data")
            setLoading(true)
        } catch (err) {
        }
    }

    const getProductByCategory = async (id) => {
        try {
            await embeddedGet("product/getByCategory/"+id, setProductByCategory, "data")
        }catch (err){}
    }

    useEffect(() => {
        getCategorys()
        getProducts()
    }, [])
    console.log(productByCategory)

    return (
        <>
            {loading ? (
                <div className="col-12 d-flex align-items-center justify-content-center flex-column">
                    <div className="boxs col-10">
                        <button className="cards col-4 col-md-2 col-sm-3">
                            <h5>hamma taomlar</h5>
                        </button>
                        {category.map(item => (
                            <button className="cards col-4 col-md-2 col-sm-3"
                                    onClick={() => getProductByCategory(item.id)}>
                                <h5>{item.nameUz}</h5>
                            </button>
                        ))}
                    </div>
                    {/*<>*/}
                    {/*    {productByCategory.map(item=> {*/}
                    {/*        {item}*/}
                    {/*    })}*/}
                    {/*</>*/}
                    <div className="col-10 row">
                        {search.length === 0 ? (
                            getProductAll(product)
                        ) : (
                            getProductAll(filter)
                        )}
                    </div>
                </div>
            ) : (
                <Loader/>
            )}
        </>
    )
}

const getProductAll = (data) => {
    return (
        data.map(item1 => (
            <div style={{zIndex: '10'}} className="card col-12 col-md-4 col-sm-6 col-lg-3 col-xl-3 col-xxl-3">
                <img
                    src={item1.img}
                    className="card-img-top" alt={item1.id}/>
                <div className="card-body">
                    <h5 className="card-title">{item1.nameUz}</h5>
                    <p className="card-text">{item1.description}</p>
                    <p className="card-text">{item1.price}</p>
                    <Link to="/zakaz" className="btn btn-primary">{item1.category.nameUz}</Link>
                </div>
            </div>
        ))
    )
}