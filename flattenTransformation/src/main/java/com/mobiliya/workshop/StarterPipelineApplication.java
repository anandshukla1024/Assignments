package com.mobiliya.workshop;

import com.mobiliya.workshop.dataflow.pipeline.steps.DataflowPipelineBuilder;

import java.io.IOException;

/**
 * Provide description here
 * @version 0.1
 */
public class StarterPipelineApplication {

    public static void main(String[] args) throws IOException {
        new DataflowPipelineBuilder().createDataPipeline(args).run();
    }
}
