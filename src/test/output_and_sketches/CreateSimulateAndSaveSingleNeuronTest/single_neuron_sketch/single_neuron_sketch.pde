import gifAnimation.*;

final String dataDirectory = dataPath("");

final int WIDTH_IN_PIXELS = 1000;
final int HEIGHT_IN_PIXELS = 1000;

final int BRANCH_STROKE_WEIGHT = 50;
final float SIGNAL_STROKE_WEIGHT_SCALING_COEFF = 1;

int numberOfFramesInGif = 100;
int startFrame = 1;
int frame;

float conversionCoefficientNeuronCoordinatesPerPixelXAxis;
float conversionCoefficientNeuronCoordinatesPerPixelYAxis;

void setup() {
  
  size(WIDTH_IN_PIXELS, HEIGHT_IN_PIXELS);
  
  setConversionCoefficientsUsingBranchInformation();
  
  resetImageAndRemoveDrawnSignals();
  
  frame = 0;
}

void draw() {
  
  resetImageAndRemoveDrawnSignals();
  
  drawSignals();
  
  saveToImage();
  
  if (frame == numberOfFramesInGif) {
   loadSavedImagesAndSaveAsGif(); 
  }
  
  frame++;
  
}

public void resetImageAndRemoveDrawnSignals() {
  fill(#000000, 255);
  rect(0,0,width,height);
  drawBranches();
}

public void drawBranches() {
  stroke(BRANCH_STROKE_WEIGHT);
  Table branches = loadTable("branches.csv");
  for (TableRow row : branches.rows()) {
    float x1 = row.getFloat("x1");
    float y1 = row.getFloat("y1");
    float x2 = row.getFloat("x2");
    float y2 = row.getFloat("y2");
    line(x1 * conversionCoefficientNeuronCoordinatesPerPixelXAxis, 
    y1 * conversionCoefficientNeuronCoordinatesPerPixelYAxis, 
    x2 * conversionCoefficientNeuronCoordinatesPerPixelXAxis, 
    y2 * conversionCoefficientNeuronCoordinatesPerPixelYAxis);
  } 
}

public void setConversionCoefficientsUsingBranchInformation() {
  Table branches = loadTable("branches.csv", "header");
  float xmin = 100000;
  float xmax = -100000;
  float ymin = 100000;
  float ymax = -100000;
  for (TableRow row : branches.rows()) {
    if (row.getFloat("x1") < xmin) {xmin = row.getFloat("x1");}
    if (row.getFloat("x2") < xmin) {xmin = row.getFloat("x2");}
    if (row.getFloat("x1") > xmax) {xmax = row.getFloat("x1");}
    if (row.getFloat("x2") > xmax) {xmax = row.getFloat("x2");}
    if (row.getFloat("y1") < ymin) {ymin = row.getFloat("y1");}
    if (row.getFloat("y2") < ymin) {ymin = row.getFloat("y2");}
    if (row.getFloat("y1") > ymax) {ymax = row.getFloat("y1");}
    if (row.getFloat("y2") > ymax) {ymax = row.getFloat("y2");}
  }
  conversionCoefficientNeuronCoordinatesPerPixelXAxis = (xmax-xmin)/width;
  conversionCoefficientNeuronCoordinatesPerPixelYAxis = (ymax-ymin)/height;
}

public void drawSignals() {
  Table signalsForThisFrame = loadTable(frame+"_allSignals.csv", "header");
  for (TableRow signal : signalsForThisFrame.rows()) {
    if (signal.getString("type") == "SquareSignal") {drawSquareSignal(signal);}
    else { throw new IllegalArgumentException("Signal type not recognised");}
  }
}

public void drawSquareSignal(TableRow signal) {
  float mean_loc_x = signal.getFloat("mean_loc_x");
  float mean_loc_y = signal.getFloat("mean_loc_y");
  float direction_x = signal.getFloat("direction_x");
  float direction_y = signal.getFloat("direction_y");
  float amplitude = signal.getFloat("amplitude");
  float signal_width = signal.getFloat("width");
  
  float x1 = mean_loc_x - signal_width * cos(atan(direction_x/direction_y));
  float x2 = mean_loc_x + signal_width * cos(atan(direction_x/direction_y));
  
  float y1 = mean_loc_y - signal_width * sin(atan(direction_x/direction_y));
  float y2 = mean_loc_y + signal_width * sin(atan(direction_x/direction_y));
  
  stroke(round(amplitude*SIGNAL_STROKE_WEIGHT_SCALING_COEFF));
  line(x1 * conversionCoefficientNeuronCoordinatesPerPixelXAxis, 
    y1 * conversionCoefficientNeuronCoordinatesPerPixelYAxis, 
    x2 * conversionCoefficientNeuronCoordinatesPerPixelXAxis, 
    y2 * conversionCoefficientNeuronCoordinatesPerPixelYAxis);
}
