import React from 'react'
import {Link, useLocation} from 'react-router-dom'
import {useMediaQuery} from 'react-responsive'

export const Sidebar = ({toggle, clickToggle}) => {
    const isMobile = useMediaQuery({maxWidth: 1199})
    const role = localStorage.getItem('role')
    const location = useLocation().pathname
    const uz = localStorage.getItem('uzLan')
    const en = localStorage.getItem('enLan')
    const ru = localStorage.getItem('ruLan')
    const navLinkInfos = [
        {
            title: "category",
            link: '/admin/category',
            icon: "fa-solid fa-list-dropdown"
        }, {
            title: "product",
            link: "/admin/product",
            icon: ""
        },{
            title: "aware",
            link: "/admin/aware",
            icon: ""
        }, {
            title: "menyu",
            link: "/",
            icon: ""
        }
    ]

    return (
        <div className={toggle ? 'toggle-sidebar' : ""}>
            <aside id="sidebar" className="sidebar">

                <ul className="sidebar-nav" id="sidebar-nav">

                    {navLinkInfos.map(item => (
                        <li className={'nav-item'} key={item.link}>
                            <Link
                                className={location === item.link ? `nav-link pb-0 bg-color row justify-content-center align-items-center` : 'nav-link pb-0 row justify-content-center align-items-center'}
                                to={item.link} onClick={isMobile && clickToggle}>
                                    <span className={'card-title pb-0 pt-0'}>
                                        <i className={location === item.link ? `${item.icon} text-white` : `${item.icon}`}
                                           style={{fontSize: '26px'}}/>
                                    </span>
                                <span
                                    className={location === item.link ? 'text-white card-title pb-0 pt-0' : 'card-title pb-0 pt-0'}>{item.title}
                                    <view/>
                                    </span>
                            </Link>
                        </li>
                    ))}
                </ul>
            </aside>

        </div>
    )
}