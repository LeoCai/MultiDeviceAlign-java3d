package com.nju.leocai.core;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class PhoneView {

	private TransformGroup tg;
	private Transform3D transform;

	public PhoneView() {
		SimpleUniverse universe = new SimpleUniverse();

		BranchGroup group = new BranchGroup();
		Appearance appearance = new Appearance();
		appearance.setPolygonAttributes(
				new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_BACK, 0.0f));

		Box sphere = new Box(0.5f, 0.2f, 0.2f, appearance);

		transform = new Transform3D();
		Matrix3d matrix3d = new Matrix3d(1, 0, 0, 0, 1, 0, 0, 0, 1);
		transform.set(matrix3d);

		tg = new TransformGroup();
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		tg.setTransform(transform);

		tg.addChild(sphere);

		group.addChild(tg);

		Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light

		BoundingSphere bounds =

		new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

		DirectionalLight light1

		= new DirectionalLight(light1Color, light1Direction);

		light1.setInfluencingBounds(bounds);

		group.addChild(light1);

		universe.getViewingPlatform().setNominalViewingTransform();

		universe.addBranchGraph(group);
	}
	
	public void setTransform(double x, double y){
		transform = new Transform3D();
		Matrix3d matrix3d = new Matrix3d(1, x, 0, 0, 1, y, 0, 0, 1);
		transform.set(matrix3d);
		tg.setTransform(transform);
	}

	public static void main(String args[]) {
		new Thread(new Runnable() {
			PhoneView d1 = new PhoneView();
			private double y;
			private double x;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					d1.setTransform(x, y);
					x+=0.5;
					y+=0.5;
//					System.out.println(""+x+","+y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
//		d1.setTransform();
	}
}
