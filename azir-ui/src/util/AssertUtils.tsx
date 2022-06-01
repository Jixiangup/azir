import { allowAccess } from "@/services/azir/menu"
import { history } from "umi";

const path403 = '/403'

export const assertPath = () => {
    const { location } = history;
    allowAccess(location.pathname).then(response => {
        const { status, data } = response;
        if (status && !data) {
            history.push(path403)
        }
    })
}