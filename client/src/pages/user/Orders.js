import React, { useState, useEffect } from "react";
import UserMenu from "../../components/Layout/Usermenu";
import Layout from "./../../components/Layout/Layout";
import toast from "react-hot-toast";
import axios from "axios";
import { useAuth } from "../../context/auth";
import moment from "moment";

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [auth, setAuth] = useAuth();

  const getOrders = async () => {
    try {
      const { data } = await axios.get("/api/v1/auth/orders");
      setOrders(data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    if (auth?.token) getOrders();
  }, [auth?.token]);

  const handleCancelOrder = async (orderId) => {
    try {
      const { data } = await axios.put(`/api/v1/auth/cancel-order/${orderId}`);
      // Display success message or perform any other action upon cancellation
      toast.success("Product cancelled Successfully");
      console.log(data);
      getOrders();
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Layout title={"Your Orders"}>
      <div className="container-fluid p-3 m-3 dashboard">
        <div className="row">
          <div className="col-md-3">
            <UserMenu />
          </div>
          <div className="col-md-9">
            <h1 className="text-center">All Orders</h1>
            {orders?.map((o, i) => (
              <div className="border shadow" key={o._id}>
                <table className="table">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Status</th>
                      <th scope="col">Buyer</th>
                      <th scope="col">Date</th>
                      <th scope="col">Payment</th>
                      <th scope="col">Quantity</th>
                      <th scope="col">Actions</th> {/* Added Actions column */}
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>{i + 1}</td>
                      <td>{o?.status}</td>
                      <td>{o?.buyer?.name}</td>
                      <td>{moment(o?.createdAt).format("MMMM Do YYYY, h:mm:ss a")}</td>
                      <td>{o?.payment?.success ? "Success" : "Failed"}</td>
                      <td>{o?.products?.length}</td>
                      <td>
                        {o?.status !== "Canceled" && ( // Only show cancel button if the order status is not already cancelled
                          <button
                            className="btn btn-danger"
                            onClick={() => handleCancelOrder(o._id)}
                          >
                            Cancel Order
                          </button>
                        )}
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div className="container">
                  {o?.products?.map((p, i) => (
                    <div className="row mb-2 p-3 card flex-row" key={p?.product?._id}>
                      <div className="col-md-4">
                        <img
                          src={`/api/v1/product/product-photo/${p?.product?._id}`}
                          className="card-img-top"
                          alt={p?.product?.name}
                          width="100px"
                          height="250px"
                        />
                      </div>
                      <div className="col-md-8">
                        <p>{p?.product?.name}</p>
                        <p>{p?.product?.description && p?.product?.description.substring(0, 30)}</p>
                        <p>Price: {p?.product?.price}$</p>

                        {/* Display rental data */}
                        {p?.rentalDays && p?.rentalPrice ? (
                          <div>
                            <p>Rental Days: {p.rentalDays}days</p>
                            <p>Rental Price: {p.rentalPrice}$</p>
                          </div>
                        ) : (
                          <div>
                            <p></p>
                          </div>
                        )}

                        <p>Net Payable Price: {o?.netPayablePrice}$</p>
                        <p>Buying Option: {o?.buyingOption}</p>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default Orders;
