package project4_weather;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.Text;
public class project4jobcommiter {

	public static void main(String[] args) throws IOException, ClassNotFoundException,InterruptedException {
		// TODO Auto-generated method stub
		Configuration cf = new Configuration();
		Job weather = Job.getInstance(cf);
		weather.setJarByClass(project4jobcommiter.class);
		weather.setMapperClass(project4mapper.class);
		weather.setReducerClass(project4reducer.class);
		weather.setMapOutputKeyClass(Text.class);
		weather.setMapOutputValueClass(DoubleWritable.class);
		weather.setOutputKeyClass(Text.class);
		weather.setOutputValueClass(DoubleWritable.class);
		
		FileInputFormat.setInputPaths(weather, args[0]);
		FileOutputFormat.setOutputPath(weather, new Path(args[1]));
		
		weather.waitForCompletion(true);
	}

}
