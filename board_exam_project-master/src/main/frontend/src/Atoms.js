import { atom } from "recoil";

const loginState = atom({
  key: "login", // unique ID (with respect to other atoms/selectors)
  default: "False", // default value (aka initial value)
});

export default loginState;
