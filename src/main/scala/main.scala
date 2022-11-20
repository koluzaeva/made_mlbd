import java.io._
import breeze.linalg._
import breeze.numerics.{pow, sqrt}
import scala.collection.mutable.{ListBuffer}


class LinearRegression(epochs: Int, learning_rate: Double) {
  var weights: DenseVector[Double] = DenseVector[Double]()
  var train_loss: List[Double] = List[Double]()
  var val_loss: List[Double] = List[Double]()

  def lossRMSE(y_pred: DenseVector[Double], y_true: DenseVector[Double]): Double = {
    sqrt(sum(pow((y_true - y_pred), 2)) / y_true.length)
   }

  def fit(X: DenseMatrix[Double], y: DenseVector[Double], val_size: Double): Unit = {

    this.weights = DenseVector.zeros[Double](X.cols)
    val idx = scala.math.ceil(X.rows * val_size).toInt

    val X_train = X(0 until idx, 0 until X.cols)
    val X_val = X(idx until X.rows, 0 until X.cols)

    val y_train = y(0 until idx)
    val y_val = y(idx until y.length)

    val val_loss = new ListBuffer[Double]()
    val train_loss = new ListBuffer[Double]()

    for (_ <- 1 to this.epochs) {
      val preds = this.predict(X_train)
      val gradient = 2.0 * X_train.t * (preds - y_train) / (y_train.length: Double)
      this.weights = this.weights - this.learning_rate * gradient

      train_loss += this.lossRMSE(this.predict(X_train), y_train)
      val_loss += this.lossRMSE(this.predict(X_val), y_val)


    this.train_loss = train_loss.toList
    this.val_loss = val_loss.toList
    }
  }

  def predict(X: DenseMatrix[Double]): DenseVector[Double] = {
    X * this.weights
  }
}


object Main {
  def main(args: Array[String]): Unit = {
    val epochs: Int = 1000
    val learning_rate: Double = 0.0001
    val split_size: Double = 0.75
    val validation_size: Double = 0.25

    val train = csvread(new File("data/train.csv"), ',', skipLines = 1)
    val test = csvread(new File("data/test.csv"), ',', skipLines = 1)

    val predictionFileName = new File("train_logs/prediction.txt")
    val lossFileName = new File("train_logs/learning_statistics.txt")

    var (x_train, y_train) = (train(::, 0 until train.cols - 1), train(::, train.cols - 1))
    var (x_test, y_test) = (test(::, 0 until test.cols - 1), test(::, test.cols - 1))


    val model = new LinearRegression(epochs, learning_rate)
    model.fit(x_train, y_train, validation_size)

    var preds = model.predict(x_train)
    var train_loss = model.lossRMSE(preds, y_train)
    println("Train RMSE value: " + train_loss + "\n")

    var test_preds = model.predict(x_test)
    var test_loss = model.lossRMSE(test_preds, y_test)
    println("Test RMSE value: " + test_loss + "\n")

    val learning_curve = DenseMatrix(model.train_loss, model.val_loss)
    csvwrite(lossFileName, learning_curve.t)
    println(f"Train and validation losses are saved to $lossFileName")

    csvwrite(predictionFileName, test_preds.toDenseMatrix.t)
    println(f"Predictions for test data are saved to $predictionFileName")
  }
}