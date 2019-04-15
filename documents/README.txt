This material is associated with the PhD Thesis of Javier Olias
(which is supervised by Sergio Cruces) and the article:

“EEG Signal Processing in MI-BCI Applications with Improved Covariance Matrix
Estimators” by J.Olias, R. Martin-Clemente, M.A. Sarmiento-Vega and S. Cruces,
which was accepted in the IEEE Transactions on Neural Systems & Rehabilitation
Engineering, 2019.

In this material you will find the following files:

1)	Data.mat
    Synthetic dataset of simulated EEG filtered recordings.
    It can be replaced by the datasets of the BCI competions for real testing.
    The data is stored in a MatLab struct type with the following fields:

    -x: Simulated EEG trials of dimension (n. samples)x(n. sensors)x(n. trials).
    -y: Classes of the trials in a vector of dimension (n. trials) x 1.
    -TrueCovClass: Tensor that stores the covariance of the 2 classes.
                   Its dimension is (n. sensors)x(n. sensors) x 2
2)	Demo.py:
    Python demo file that illustrates the improvements obtained with the proposed
    power-normalization of the trials contained in Data.mat. The proposal can be
    interpreted as a generalization of Tyler's method for multiclass samples.

    The demo shows the improvement in the scale-invariant Riemannian distance
    of the estimated covariance matrices of the classes with respect to their
    true values once the proposed normalization is applied.

    > The average distance before normalization is 2.84
    > The average distance after  normalization is 1.88

    It also reports the accuracy of the classification results obtained with the
    CSP+LDA classifier and the CSP+Tangent Space Logistic Regression classifiers,
    with and without normalization. The normalized versions nCSP+LDA and nCSP+TSLR
    clearly outperform the unnormalized ones CSP+LDA and CSP+TSLR.

                       CSP           nCSP
    Classifier
    LDA              0.8175         0.8475
    TSLR             0.8025         0.8700

3)	Normalization_functions.py:
    Stores the functions implementing the proposed normalization
    and the scale-invariant Riemannian distance.

4)	Auxiliary_functions: Auxiliary functions for the demo.

5)	Suplementary.pdf:
    Supplementary material of the main article with extra figures and tables.
    It presents the obtained results of the algorithms for the multi-class paradigm.

For additional inquiries you can contact us by e-mail: folias@us.es, sergio@us.es
