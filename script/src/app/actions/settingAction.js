export function fetchUser() {
    return {
        type: "FETCH_USER_FULFILLED",
        payload: {
            name: "Will",
            age: 35,
        }
    }
}

export function setUserName(name) {
    return {
        type: 'SET_USER_NAME',
        payload: name,
    }
}

export function setUserAge(age) {
    return {
        type: 'SET_USER_AGE',
        payload: age,
    }
}

export function setCircularProgress(toggle) {
    return {
        type: 'SET_CIRCULAR_PROGRESS',
        payload: toggle,
    }
}

export function setIsOpenDialog(toggle) {
    return {
        type: 'SET_OPEN_DIALOG',
        payload: toggle,
    }
}

export function setDialog(header, message) {
    return {
        type: 'SET_DIALOG',
        payload: {
            dialogHeader: header,
            dialogMessage: message,
        }
    }
}