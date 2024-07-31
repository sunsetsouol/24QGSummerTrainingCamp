import React, { createContext, useState, useContext } from "react";

// 创建一个 Context
const AdminContext = createContext();

// 创建一个 Provider 组件
export const AdminProvider = ({ children }) => {
  const [isAdmin, setIsAdmin] = useState(false); // 管理员状态

  return (
    <AdminContext.Provider value={{ isAdmin, setIsAdmin }}>
      {children}
    </AdminContext.Provider>
  );
};

// 创建一个自定义 Hook 方便使用 Context
export const useAdmin = () => useContext(AdminContext);
