import express from "express";
import colors from "colors";
import dotenv from "dotenv";
import morgan from "morgan";
import connectDB from "./config/db.js";
import authRoute from "./routes/authRoute.js";
import categoryRoutes from "./routes/categoryRoutes.js";
import ProductRoute from "./routes/productRoute.js";
import cors from "cors";
import path from "path";

//configure.env
dotenv.config();

//database config
connectDB();

//rest object
const app = express();

//middlewares
app.use(cors());
app.use(express.json());
app.use(morgan("dev"));
app.use(express.static(path.join(__dirname, "./client/build")));

//routes
app.use("/api/v1/auth", authRoute);
app.use("/api/v1/category", categoryRoutes);
app.use("/api/v1/product", ProductRoute);

//rest api
app.use("*", function (req, res) {
  res.sendFile(path.join(__dirname, "./client/build/index.html"));
});
//Port
const PORT = process.env.PORT || 5000;
//run listen
app.listen(PORT, () => {
  console.log(
    `serving Running on ${process.env.DEV_MODE}mode on port ${PORT}`.bgCyan
      .white
  );
});
