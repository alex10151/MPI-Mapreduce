package project4_weather;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class project4reducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	public void reduce(Text _key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		double max = 0.0;
		double min = 40.0;
		for (DoubleWritable val : values) {
			if(val.get() > max)
				max = val.get();
			if(val.get() < min)
				min = val.get();
		}
		context.write(new Text(_key.toString()+"  max temperature is:"), new DoubleWritable(max));
		context.write(new Text(_key.toString()+" min temperature is:"), new DoubleWritable(min));
	}

}
