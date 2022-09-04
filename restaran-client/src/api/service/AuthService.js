import React from "react";
import {toast} from "react-toastify";
import {baseConfigurer} from "../baseConfig/baseConfigurer";

export const login = async (data) => {
    const check = {
        phoneNumber: data.phoneNumber.trim().length === 0,
        password: data.password.trim().length === 0
    }
    if (check.phoneNumber || check.password) {
        toast.warning("malumot kirgizing")
        return
    }

    try {
        const res = await baseConfigurer.doPost("auth/login", data)
        if (res.status === 200) {
            const roles = res.data.user.roles.length > 2 ? "SUPER_ADMIN" : res.data.user.roles.length > 1 ? "Admin" : res.data.user.roles.length > 0 ? "Teacher" : "undefined"
            localStorage.setItem('token', res.data.resToken.body)
            localStorage.setItem('tokenType', res.data.resToken.tokenType)
            localStorage.setItem('firstName', res.data.user.firstName)
            localStorage.setItem('lastName', res.data.user.lastName)
            localStorage.setItem('role', roles)
            localStorage.setItem('id', res.data.user.id)
            toast.success("kuting...")
            setTimeout(() => {
                window.location.reload()
            }, 2000)
        }
    } catch (err) {
        if (err.response === undefined) {
            return toast.error("internetga ulaning oka")
        }
    }
}