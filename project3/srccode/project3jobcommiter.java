package project3_wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.Text;

public class project3jobcommiter {

	public static void main(String[] args)throws IOException, ClassNotFoundException,InterruptedException {
		// TODO Auto-generated method stub
		Configuration cf = new Configuration();
		Job wordcount = Job.getInstance(cf);
		wordcount.setJarByClass(project3jobcommiter.class);
		wordcount.setMapperClass(project3mapper.class);
		wordcount.setReducerClass(project3reducer.class);
		wordcount.setMapOutputKeyClass(Text.class);
		wordcount.setMapOutputValueClass(IntWritable.class);
		wordcount.setOutputKeyClass(Text.class);
		wordcount.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(wordcount,args[0]);
		FileOutputFormat.setOutputPath(wordcount, new Path(args[1]));
		wordcount.waitForCompletion(true);
	}

}
