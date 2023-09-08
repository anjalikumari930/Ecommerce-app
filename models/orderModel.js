import mongoose from "mongoose";

const orderSchema = new mongoose.Schema(
  {
    products: [
      {
        product: {
          type: mongoose.ObjectId,
          ref: "Products",
        },
        rentalDays: {
          type: Number,
          default: 0,
        },
        rentalPrice: {
          type: Number,
          default: 0,
        },
      },
    ],
    payment: {},
    buyer: {
      type: mongoose.ObjectId,
      ref: "users",
    },
    status: {
      type: String,
      default: "Not Process",
      enum: ["Not Process", "Processing", "Shipped", "Delivered", "Canceled"],
    },
    netPayablePrice: {
      type: Number,
      required: true,
    },
    buyingOption: {
      type: String,
      required: true,
      enum: ["Rental", "Not Rental"],
    },
  },
  { timestamps: true }
);

export default mongoose.model("Order", orderSchema);
