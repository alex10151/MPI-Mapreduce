package project4_weather;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class project4mapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		double temp=0.0;
		String []line =ivalue.toString().split(",");
		if((line[1].charAt(0)>57) || (line[1].charAt(0)<49))
			return ;
		temp = Double.parseDouble(line[1].trim());
		context.write(new Text("this day's"),new DoubleWritable(temp));
		
	}

}
